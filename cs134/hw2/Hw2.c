#include <GL/glew.h>
#include <SDL2/SDL.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include "DrawUtils.h"

#define BG_SIZE_WIDTH 40
#define BG_SIZE_HEIGHT 40
#define WINDOW_WIDTH 640
#define WINDOW_HEIGHT 480

int main(void) {
    // Initialize SDL
    if (SDL_Init(SDL_INIT_VIDEO) < 0) {
        return 1;
    }

    // Create the window, OpenGL context
    SDL_GL_SetAttribute(SDL_GL_BUFFER_SIZE, 32);
    SDL_GL_SetAttribute(SDL_GL_DOUBLEBUFFER, 1);
    SDL_Window* window = SDL_CreateWindow(
        "Press the Arrow Keys to make things happen",
        SDL_WINDOWPOS_UNDEFINED, SDL_WINDOWPOS_UNDEFINED,
        WINDOW_WIDTH,
        WINDOW_HEIGHT,
        SDL_WINDOW_OPENGL);

    if (!window) {
        fprintf(stderr, "Could not create window. Error Code = %s\n", SDL_GetError());
        SDL_Quit();
        return 1;
    }
    SDL_GL_CreateContext(window);

    // Make sure we have a recent version of OpenGL
    GLenum glewError = glewInit();
    if (glewError != GLEW_OK) {
        fprintf(stderr, "Could not initialize glew. Error Code = %s\n", glewGetErrorString(glewError));
        SDL_Quit();
    }
    if (!GLEW_VERSION_3_0) {
        fprintf(stderr, "OpenGL max supported version is too low now.\n");
        SDL_Quit();
        return 1;
    }

    // Setup OpenGL state
    glViewport(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    glMatrixMode(GL_PROJECTION);
    glOrtho(0, WINDOW_WIDTH, WINDOW_HEIGHT, 0, 0, 100);
    glEnable(GL_TEXTURE_2D);
    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    // Create the background texture array. Going to load
    // everything at the same time for now. Maybe there
    // is a more efficient way to load this later
    GLuint lambda = glTexImageTGAFile("lambda.tga", NULL, NULL);
    GLuint aperture = glTexImageTGAFile("aperture.tga", NULL, NULL);
    int ryuWidth, ryuHeight;
    GLuint ryu = glTexImageTGAFile("ryu.tga", &ryuWidth, &ryuHeight);

    // Logic to keep track of keyboard pushes
    unsigned char kbPrevState[SDL_NUM_SCANCODES] = {0};
    const unsigned char* kbState = NULL;
    kbState = SDL_GetKeyboardState(NULL);

    // Need to keep track of when to redraw frames
    Uint32 lastFrameMs = 0;
    Uint32 currentFrameMs = SDL_GetTicks();

    // Some initialization for the background
    int background[40][40];
    for (int i = 0; i < 40; i++) {
        for (int j = 0; j < 40; j++) {
            // Generate random tiles for now
            background[i][j] = (rand() % 2 == 0) ? 0 : 1;
        }
    }

    // Set options for camera coordinates to draw background
    float camX = 0;
    float camY = 0;

    // The game loop
    char shouldExit = 0;
    while (!shouldExit) {
        // kbState is updated by the message pump. Copy over the old state before the pump!
        lastFrameMs = currentFrameMs;
        memcpy(kbPrevState, kbState, sizeof(kbPrevState));

        // Handle OS message pump
        SDL_Event event;
        while (SDL_PollEvent(&event)) {
            switch(event.type) {
                case SDL_QUIT:
                    shouldExit = 1;
            }
        }

        // Going to handle keyboard events here
        kbState = SDL_GetKeyboardState(NULL);
        if (kbState[SDL_SCANCODE_RIGHT]) {
            camX = (camX < 39) ? camX += 0.1 : camX;
        }
        if (kbState[SDL_SCANCODE_LEFT]) {
            camX = (camX > 0) ? camX -= 0.1 : camX;
        }
        if (kbState[SDL_SCANCODE_UP]) {
            camY = (camY > 0) ?  camY -= 0.1: camY;
        }
        if (kbState[SDL_SCANCODE_DOWN]) {
            camY = (camY < 39) ? camY += 0.1 : camY;
        }

        // Calculating frame updates
        currentFrameMs = SDL_GetTicks();
        float deltaTime = (currentFrameMs - lastFrameMs) / 1000.0f;

        glClearColor(1, 1, 1, 1);
        glClear(GL_COLOR_BUFFER_BIT);

        // This draws the background.
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                int tempCamX = (int) floor(camX);
                int tempCamY = (int) floor(camY);
                if (background[i + tempCamY][j + tempCamX] == 0) {
                    glDrawSprite(aperture,
                                 40 * j,
                                 40 * i,
                                 40,
                                 40);
                } else {
                    glDrawSprite(lambda,
                                 40 * j,
                                 40 * i,
                                 40,
                                 40);
                }
            }
        }

        glDrawSprite(ryu, camX * 40, camY * 40, 60, 60);
        SDL_GL_SwapWindow(window);
    }

    SDL_Quit();
    return 0;
}
