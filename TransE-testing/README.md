"Test_with_data_from_DB" Folder for perform the matching test of the Data model, Thesis Section 5.2.2.1
"Test_with_meta_data" Folder for perform the matching test of the Meta-data, Thesis Section 5.2.2.2

TransE-Testing in each of the above folders:
*  Copy and paste the file containing the raw input data (the file with the name result<test_case>.txt) into the directory "DatasetOfSensors". 
(--This file is automatically generated by the Java program that is found under the kgeDataZIMOCreation folder.
 --However, there are already created files in createdDataForTest Folder)
*  Make sure that this directory contains only one result file. 
*  Make sure that the DatasetOfSensors directory contains the file category2id.txt
*  Then change the working directory to either "TransE - "Folder. Execute the main.py file with the
python3 main.py command from the terminal. 
*  Do not worry about the other files in the directory DatasetOfSensors. The program will delete the unnecessary files automatically. 
{\rtf1}{\rtf1}