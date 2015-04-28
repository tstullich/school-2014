"""
Script adapted from:
Thierry Bertin-Mahieux (2011) Columbia University
   tb2332@columbia.edu
   Copyright 2011 T. Bertin-Mahieux, All Rights Reserved

"""
import csv
import os
import sys
import time
import glob
import datetime
import sqlite3
import numpy as np # get it at: http://numpy.scipy.org/
# path to the Million Song Dataset subset (uncompressed)
# CHANGE IT TO YOUR LOCAL CONFIGURATION
msd_subset_path='/home/tim/school-2015/cs185c/MillionSongSubset'
msd_subset_data_path=os.path.join(msd_subset_path,'data')
msd_subset_addf_path=os.path.join(msd_subset_path,'AdditionalFiles')
assert os.path.isdir(msd_subset_path),'wrong path' # sanity check
# path to the Million Song Dataset code
# CHANGE IT TO YOUR LOCAL CONFIGURATION
msd_code_path='/home/tim/school-2015/cs185c/MSongsDB'
assert os.path.isdir(msd_code_path),'wrong path' # sanity check
# we add some paths to python so we can import MSD code
# Ubuntu: you can change the environment variable PYTHONPATH
# in your .bashrc file so you do not have to type these lines
sys.path.append( os.path.join(msd_code_path,'PythonSrc') )

# imports specific to the MSD
import hdf5_getters as GETTERS

# useful function to iterate the files
def apply_to_all_files(basedir,func=lambda x: x,ext='.h5'):
    """
    From a base directory, go through all subdirectories,
    find all files with the given extension, apply the
    given function 'func' to all of them.
    If no 'func' is passed, we do nothing except counting.
    INPUT
       basedir  - base directory of the dataset
       func     - function to apply to all filenames
       ext      - extension, .h5 by default
    RETURN
       number of files
    """
    cnt = 0
    # iterate over all files in all subdirectories
    for root, dirs, files in os.walk(basedir):
        files = glob.glob(os.path.join(root,'*'+ext))
        # count files
        cnt += len(files)
        # apply function to all files
        for f in files :
            func(f)       
    return cnt


# function to convert artist terms from array to string
def convert_terms(artist_terms):
    return str(artist_terms).strip('[]\n')

# we define the function to apply to all files
def get_all_attributes(filename):
    """
    This function does 3 simple things:
    - open the song file
    - get all required attributes
    - write it to a csv file 
    - close the files
    """
    with open('attributes.csv', 'a') as csvfile:
        try:
            # let's apply the previous function to all files
            csvwriter = csv.writer(csvfile, delimiter='\t')
            h5 = GETTERS.open_h5_file_read(filename)
            RESULTS = []
            RESULTS.append(GETTERS.get_year(h5))
            RESULTS.append(GETTERS.get_artist_id(h5))
            RESULTS.append(GETTERS.get_artist_name(h5))
            RESULTS.append(GETTERS.get_artist_mbid(h5))
            RESULTS.append(convert_terms(GETTERS.get_artist_terms(h5)))
            RESULTS.append(GETTERS.get_artist_hotttnesss(h5))
            RESULTS.append(GETTERS.get_artist_latitude(h5))
            RESULTS.append(GETTERS.get_artist_longitude(h5))
            RESULTS.append(GETTERS.get_artist_familiarity(h5))
            RESULTS.append(GETTERS.get_danceability(h5))
            RESULTS.append(GETTERS.get_duration(h5))
            RESULTS.append(GETTERS.get_energy(h5))
            RESULTS.append(GETTERS.get_loudness(h5))
            RESULTS.append(GETTERS.get_song_hotttnesss(h5))
            RESULTS.append(GETTERS.get_song_id(h5))
            RESULTS.append(GETTERS.get_tempo(h5))
            RESULTS.append(GETTERS.get_time_signature(h5))
            RESULTS.append(GETTERS.get_title(h5))
            RESULTS.append(GETTERS.get_track_id(h5))
            RESULTS.append(GETTERS.get_release(h5))
            csvwriter.writerow(RESULTS)
            h5.close()
        except AttributeError:
            pass

apply_to_all_files(msd_subset_data_path, func=get_all_attributes)
