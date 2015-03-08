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

typedef struct AABB {
    int x, y, w, h;
} AABB;

typedef struct Player {
    float posX;
    float posY;
    AABB box;
} Player;

typedef struct Camera {
    int posX;
    int posY;
} Camera;

void updatePlayer(Player, int);
void updateCamera(Camera, int);
bool AABBIntersect(const AABB*, const AABB*);

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
    Camera camera;
    camera.posX = 0;
    camera.posY = 0;

    // Set options for the player coordinates
    Player player;
    player.posX = 320;
    player.posY = 240;

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

        // Going to handle keyboard events to move the camera or player
        kbState = SDL_GetKeyboardState(NULL);
        if (kbState[SDL_SCANCODE_RIGHT]) {
            player.posX = (player.posX < 640) ? player.posX += 1 : player.posX;
        }
        if (kbState[SDL_SCANCODE_LEFT]) {
            player.posX = (player.posX > 0) ? player.posX -= 1 : player.posX;
        }
        if (kbState[SDL_SCANCODE_UP]) {
            player.posY = (player.posY > 0) ? player.posY -= 1: player.posY;
        }
        if (kbState[SDL_SCANCODE_DOWN]) {
            player.posY = (player.posY < 640) ? player.posY += 1 : player.posY;
        }

        if (kbState[SDL_SCANCODE_D]) {
            camera.posX = (camera.posX < 640) ? camera.posX += 4 : camera.posX;
        }
        if (kbState[SDL_SCANCODE_A]) {
            camera.posX = (camera.posX > 0) ? camera.posX -= 4 : camera.posX;
        }
        if (kbState[SDL_SCANCODE_W]) {
            camera.posY = (camera.posY > 0) ? camera.posY -= 4: camera.posY;
        }
        if (kbState[SDL_SCANCODE_S]) {
            camera.posY = (camera.posY < 640) ? camera.posY += 4 : camera.posY;
        }

        // Calculating frame updates
        currentFrameMs = SDL_GetTicks();
        float deltaTime = (currentFrameMs - lastFrameMs) / 1000.0f;

        glClearColor(1, 1, 1, 1);
        glClear(GL_COLOR_BUFFER_BIT);

        // This draws the background.
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                if (background[i][j] == 0) {
                    glDrawSprite(aperture,
                                 (j * 40) - camera.posX,
                                 (i * 40) - camera.posY,
                                 40,
                                 40);
                } else {
                    glDrawSprite(lambda,
                                 (j * 40) - camera.posX,
                                 (i * 40) - camera.posY,
                                 40,
                                 40);
                }
            }
        }

        glDrawSprite(ryu, player.posX - camera.posX, player.posY - camera.posY, 60, 60);
        SDL_GL_SwapWindow(window);
    }

    SDL_Quit();
    return 0;
}

void updatePlayer(Player player, int deltaTime) {
}

void updateCamera(Camera player, int deltaTime) {
}

bool AABBIntersect( const AABB* box1, const AABB* box2 ) {
    // box1 to the right
    if( box1->x > box2->x + box2->w ) {
        return false;
    }

    // box1 to the left
    if( box1->x + box1->w < box2->x ) {
        return false;
    }

    // box1 below
    if( box1->y > box2->y + box2->h ) {
        return false;
    }
    // box1 above
    if( box1->y + box1->h < box2->y ) {
        return false;
    }
    return true;
}
