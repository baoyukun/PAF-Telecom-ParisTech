#coding='utf-8'
#Implementation of Rapid Automatic Keyword Extraction algorithm.
#Author: Vishwas B Sharma, BAO Yukun

import string
import json
from collections import defaultdict
from itertools import chain, groupby, product
import nltk
from nltk.tokenize import wordpunct_tokenize

class Rake(object):

    def __init__(self, language):
        # We use language stopwords by default.
        self.stopwords = nltk.corpus.stopwords.words(language)

        # We ignore all punctuation symbols.
        self.punctuations = list(string.punctuation)

        # All things which act as sentence breaks during keyword extraction.
        self.to_ignore = set(self.stopwords + self.punctuations)

        # Stuff to be extracted from the provided text.
        self.frequency_dist = None
        self.degree = None
        self.rank_list = None
        self.ranked_phrases = None

    def extract_keywords_from_text(self, text):
        sentences = nltk.tokenize.sent_tokenize(text)
        self.extract_keywords_from_sentences(sentences)

    def extract_keywords_from_sentences(self, sentences):
        phrase_list = self._generate_phrases(sentences)
        self._build_frequency_dist(phrase_list)
        self._build_word_co_occurance_graph(phrase_list)
        self._build_ranklist(phrase_list)

    def get_ranked_phrases(self):
        return self.ranked_phrases

    def get_ranked_phrases_with_scores(self):
        return self.rank_list

    def get_word_frequency_distribution(self):
        return self.frequency_dist

    def get_word_degrees(self):
        return self.degree

    def _build_frequency_dist(self, phrase_list):
        self.frequency_dist = defaultdict(lambda: 0)
        for word in chain.from_iterable(phrase_list):
            self.frequency_dist[word] += 1

    def _build_word_co_occurance_graph(self, phrase_list):
        co_occurance_graph = defaultdict(lambda: defaultdict(lambda: 0))
        for phrase in phrase_list:
            for (word, coword) in product(phrase, phrase):
                co_occurance_graph[word][coword] += 1
        self.degree = defaultdict(lambda: 0)
        for key in co_occurance_graph:
            self.degree[key] = sum(co_occurance_graph[key].values())

    def _build_ranklist(self, phrase_list):
        self.rank_list = []
        for phrase in phrase_list:
            rank = 0.0
            for word in phrase:
                rank += 1.0 * self.degree[word] / self.frequency_dist[word]
            self.rank_list.append((rank, ' '.join(phrase)))
        self.rank_list.sort(reverse=True)
        self.ranked_phrases = [ph[1] for ph in self.rank_list]

    def _generate_phrases(self, sentences):
        phrase_list = set()
        # Create contender phrases from sentences.
        for sentence in sentences:
            word_list = [word.lower() for word in wordpunct_tokenize(sentence)]
            phrase_list.update(self._get_phrase_list_from_words(word_list))
        return phrase_list

    def _get_phrase_list_from_words(self, word_list):
        phrase_list = []
        for group in groupby(word_list, lambda x: x in self.to_ignore):
            if not group[0]:
                phrase_list.append(tuple(group[1]))
        return phrase_list

def importJson(path):
    with open(path, 'rb') as inFile:
        s=inFile.read().decode('utf-8')
    return json.loads(s)

def exportJson(path, obj):
    with open(path,'wb') as outFile:
        outFile.write(json.dumps(obj,sort_keys=True,indent=2).encode('utf-8'))

def addKeyword(x0, x, x0_len):
    y = [i for i in x if len(i.split())<5]
    if len(y) <= 5-x0_len:
        return x0+y
    else:
        for i in range(5-x0_len):
            x0.append(y[i])
        return x0

def main(path):
    obj = importJson(path)

    for article in obj['articles']:
        keyword = article['keywords']
        abstract = article['abstract']
        keywordLen = len(keyword)

        if len(abstract)>0 and keywordLen<5:
            if article['lang']=='fr':
                r = Rake('french')
            else:
                r = Rake('english')

            r.extract_keywords_from_text(abstract)
            newKeyword = r.get_ranked_phrases()

            keyword = addKeyword(keyword, newKeyword, keywordLen)
            article['keywords'] = keyword

    exportJson(path, obj)

main('F:\WorkSpace\papers\paperJson.json')
