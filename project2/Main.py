#!/usr/bin/env python3
# -*- coding: utf-8 -*-
# Hilal Balcı, 150114047
# Atakan Ülgen, 150115066
# Şükrü Gümüştaş, 150114032
from __future__ import division

import io
import re
# noinspection PyCompatibility
import urllib.request

import matplotlib.pyplot as plt
import nltk
from pdfminer.converter import TextConverter
from pdfminer.layout import LAParams
from pdfminer.pdfinterp import PDFResourceManager, PDFPageInterpreter
from pdfminer.pdfpage import PDFPage
from wordcloud import WordCloud
from stop_words_library import wide_range_stopwords
from tf_idf_library import tf_word_list_creator, tf_idf_word_list_creator


def convert(fname, pages=None):
    if not pages:
        pagenums = set()
    else:
        pagenums = set(pages)

    output = io.StringIO()
    manager = PDFResourceManager()
    converter = TextConverter(manager, output, laparams=LAParams())
    interpreter = PDFPageInterpreter(manager, converter)
    #  link, temp = urllib.request.urlretrieve(fname)  #    Activate this line if you are using url
    infile = open(fname, 'rb')  #   Change fname with "link" here if you are using url.
    for page in PDFPage.get_pages(infile, pagenums):
        interpreter.process_page(page)
    infile.close()
    converter.close()
    text = output.getvalue()
    output.close()
    return text


def splitwords(words):
    cleandoc = []
    for word in words:
        word = cleaneachdocument(word)
        cleandoc.append(word)
    return cleandoc


def cleaneachdocument(document_to_clean):
    document_to_clean = document_to_clean.lower()
    document_to_clean = re.sub(r"[^\w\s]", ' ', document_to_clean)
    document_to_clean = re.sub(r"(^|\W)\d+($|\W)", ' ', document_to_clean)
    document_to_clean = re.sub(r"[0-9]+", ' ', document_to_clean)
    document_to_clean = re.sub('\s+', ' ', document_to_clean).strip()
    document_to_clean = nltk.word_tokenize(document_to_clean)
    document_to_clean = [word for word in document_to_clean if word not in wide_range_stopwords]
    return document_to_clean


def csvfile(all_documents, string):
    length = len(all_documents)
    i = 0
    while i < length:
        filename = "document_" + str(i + 1) + string + "_list.csv"
        file = open(filename, 'w', encoding='utf-8')
        for peer in all_documents[i]:
            file.write("%s,%.16f\n" % (peer[0], peer[1]))
        file.close()
        i += 1
    return


def wordcloudfile(all_documents, string):
    length = len(all_documents)
    i = 0
    while i < length:
        filename = "document_" + str(i + 1) + string + "_WordCloud.pdf"
        text = ' '.join(word[0] for word in all_documents[i])
        wordcloud = WordCloud(width=2000, height=1000, background_color="white", relative_scaling=1.0,
                              stopwords={'to', 'of'}).generate(text)
        plt.figure(figsize=(20, 10), facecolor='k')
        plt.imshow(wordcloud)
        plt.axis("off")
        plt.savefig(filename, facecolor='k', bbox_inches='tight')
        i += 1
    return


#   Activate here if you are using urls to pull files online

# doc1 = convert("https://goo.gl/FCE7fa")
# doc2 = convert("https://goo.gl/R9no1z")
# doc3 = convert("https://goo.gl/Ey6Lkw")
# doc4 = convert("https://goo.gl/Rc2PmK")
# doc5 = convert("https://goo.gl/C3SoVM")
# doc6 = convert("https://goo.gl/EtBMu3")
# doc7 = convert("https://goo.gl/ZVjV8T")
# doc8 = convert("https://goo.gl/MVWiUC")


#   Deactivate these if you are using url. If you don't want to use url or have low internet connection, you can use
#   local files under "inputs" folder.

doc1 = convert("inputs/doc1.pdf")
doc2 = convert("inputs/doc2.pdf")
doc3 = convert("inputs/doc3.pdf")
doc4 = convert("inputs/doc4.pdf")
doc5 = convert("inputs/doc5.pdf")
doc6 = convert("inputs/doc6.pdf")
doc7 = convert("inputs/doc7.pdf")
doc8 = convert("inputs/doc8.pdf")

allDoc = [doc1, doc2, doc3, doc4, doc5, doc6, doc7, doc8]

cleanwords = splitwords(allDoc)

tf_words_all_documents = []

tf_idf_words_all_documents = []

#   We are calculating all tf and tf-idf values for each unique word in each file.

for document in cleanwords:
    tf_words_all_documents.append(tf_word_list_creator(document))
    tf_idf_words_all_documents.append(tf_idf_word_list_creator(document, cleanwords))

#   After that we are creating output files seperately.

csvfile(tf_words_all_documents, "_tf")
wordcloudfile(tf_words_all_documents, "_tf")

csvfile(tf_idf_words_all_documents, "_tfidf")
wordcloudfile(tf_idf_words_all_documents, "_tfidf")
