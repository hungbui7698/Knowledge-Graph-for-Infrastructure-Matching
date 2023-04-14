import time
import os
from os import listdir
from os.path import isfile, join

def do_preprocessing():
    all_files = [f for f in listdir('../DatasetOfSensors') if isfile(join('../DatasetOfSensors', f))]
    
    result_file_index = -1
    for index, file_name in enumerate(all_files):
        if "result" in file_name:
            result_file_index = index
            break
    
    if result_file_index == -1:
        raise RuntimeError('The input file containing the raw data does not exist.')

    result_file_name = all_files[result_file_index]
    all_files.pop(result_file_index)
    if 'category2id.txt' in all_files:
        all_files.remove('category2id.txt')
    
    
    for file_name in all_files:
        os.remove('../DatasetOfSensors/' + file_name)
    time.sleep(10)
    result_file_name = os.path.abspath('../DatasetOfSensors') + '/' + result_file_name
    dataset_file_name = os.path.abspath('../DatasetOfSensors') + '/dataset.txt'
    categories_file_name = os.path.abspath('../DatasetOfSensors') + '/categories.txt'
    
    input_file = open(result_file_name, 'r')
    dataset_file = open(dataset_file_name, 'w')
    categories_file = open(categories_file_name, 'w')

    for line in input_file:
        triple = line.strip().split('\t')
        first_element = triple[0][triple[0].rindex('#') + 1 : -1]
        second_element = triple[1][triple[1].rindex('#') + 1 : -1]
        third_element = triple[2][triple[2].rindex('#') + 1 : -1]
        str_to_write = first_element + '\t' + second_element + '\t' + third_element + '\n'
        if second_element == 'type':
            categories_file.write(str_to_write)
        else:
            dataset_file.write(str_to_write)

    input_file.close()
    dataset_file.close()
    categories_file.close()
