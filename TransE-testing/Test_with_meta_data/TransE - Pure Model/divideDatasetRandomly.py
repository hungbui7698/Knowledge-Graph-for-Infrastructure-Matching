from random import shuffle

def divide_dataset_randomly():
    triples  = list()
    
    train=[]
    test=[]
    validation=[]
    
    with open("../DatasetOfSensors/dataset.txt") as f:
        for line in f:
            triples.append(line)
    f.close()
    
    shuffle(triples)
    
    numOftriples=len(triples)
    indexOfTrain=int(numOftriples*0.6)
    indexOfValid=int(indexOfTrain+numOftriples*0.2)
    
    for i in range(indexOfTrain):
        train.append(triples[i])
    
    for i in range(indexOfTrain,indexOfValid):
        validation.append(triples[i])
    
    for i in range(indexOfValid+1,len(triples)):
        test.append(triples[i])
    
    file = open("../DatasetOfSensors/trainWithName.txt","w+")
    
    for triple in train:
        file.write(triple)
    file.close()
    
    file = open("../DatasetOfSensors/testWithName.txt","w+")
    
    for triple in test:
        file.write(triple)
    file.close()
    
    file = open("../DatasetOfSensors/validWithName.txt","w+")
    
    for triple in validation:
        file.write(triple)
    file.close()
