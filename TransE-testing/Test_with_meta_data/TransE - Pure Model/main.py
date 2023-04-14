from dataset import KnowledgeGraph
from model import TransE
import os
import ray
from ray import tune
import time
import sys

import argparse
import tensorflow as tf

from generateEntities import generate_entities
from generateRelations import generate_relations
from generateDataset import generate_dataset
from divideDatasetRandomly import divide_dataset_randomly
from doPreprocessing import do_preprocessing
#graph

#tf.disable_v2_behavior()
#tf.disable_eager_execution()
def main():
    parser = argparse.ArgumentParser(description='TransE')
    parser.add_argument('--data_dir', type=str, default='../DatasetOfSensors')
    parser.add_argument('--embedding_dim', type=int, default=100)
    parser.add_argument('--margin_value', type=float, default=50)
    parser.add_argument('--score_func', type=str, default='L1')
    parser.add_argument('--batch_size', type=int, default=4800)
    parser.add_argument('--learning_rate', type=float, default=0.01)#
    parser.add_argument('--n_generator', type=int, default=24)
    parser.add_argument('--n_rank_calculator', type=int, default=24)
    parser.add_argument('--ckpt_dir', type=str, default='../ckpt/')
    parser.add_argument('--summary_dir', type=str, default='../summary/')
    parser.add_argument('--max_epoch', type=int, default=1000)
    parser.add_argument('--eval_freq', type=int, default=1000 )
    parser.add_argument('--transe_cat', type=bool, default=False)
    args = parser.parse_args()
    print(args.transe_cat)
    
    do_preprocessing()
    divide_dataset_randomly()
    generate_entities()
    generate_relations()
    generate_dataset()
    
        
    kg = KnowledgeGraph(data_dir=args.data_dir)
    kge_model = TransE(kg=kg, embedding_dim=args.embedding_dim, margin_value=args.margin_value, score_func=args.score_func, batch_size=args.batch_size, learning_rate=args.learning_rate, n_generator=args.n_generator, n_rank_calculator=args.n_rank_calculator)
    gpu_config = tf.compat.v1.GPUOptions(allow_growth=True)
    sess_config = tf.compat.v1.ConfigProto(gpu_options=gpu_config)


    with tf.compat.v1.Session(config=sess_config) as sess:
        print('-----Initializing tf graph-----')
        tf.compat.v1.global_variables_initializer().run()
        #saver = tf.compat.v1.train.Saver()
        #saver.restore(sess, "../")
        print('-----Initialization accomplished-----')
        kge_model.check_norm(session=sess)
        summary_writer = tf.compat.v1.summary.FileWriter(logdir=args.summary_dir, graph=sess.graph)
        for epoch in range(args.max_epoch):
            print('=' * 30 + '[EPOCH {}]'.format(epoch) + '=' * 30)
            kge_model.launch_training(session=sess, summary_writer=summary_writer)
            if (epoch + 1) % args.eval_freq == 0:
                kge_model.launch_evaluation(session=sess)
        #saver=tf.compat.v1.train.Saver()
        #save_path='../'
        #saver.save(sess=sess,save_path=save_path)


if __name__ == '__main__':
    main()
