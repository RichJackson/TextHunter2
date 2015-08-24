<ML-CONFIG>

--This is the features typically fo the instance (NER only)
<ATTRIBUTE>
<TYPE>Token</TYPE>
<FEATURE>string</FEATURE>
--CAN ALSO BE NUMERIC! use for numwords"!!!
<DATATYPE>nominal</DATATYPE>
</ATTRIBUTE>

-- can have a second for 
<ATTRIBUTE>
<TYPE>Token</TYPE>
<FEATURE>string</FEATURE>
--CAN ALSO BE NUMERIC! use for numwords"!!!
<DATATYPE>nominal</DATATYPE>
</ATTRIBUTE>

--This is for sentence style classificaiton tasks. works same as BL PR
<NGRAM>
<NUMBER>1</NUMBER>
<TYPE>Word</TYPE>
<FEATURE>category</FEATURE>
</NGRAM>

--for non - sequence based classificaiton
<ATTRIBUTELIST>
--this is the annotation type to extract features from (probably yhe same type as instance
<TYPE>Token</TYPE>
<FEATURE>orth</FEATURE>
<DATATYPE>nominal</DATATYPE>
-take features from -1 to 2 annotations before (inc the same type as the instance if instance == Type
<FROM>-1</FROM>
<TO>2</TO>
</ATTRIBUTELIST>

</ML-CONFIG>


libsvm params
-s 0 -t 2 -c 1 -b 1


options:
-s svm_type : set type of SVM (default 0)
	0 -- C-SVC ****
	1 -- nu-SVC
	2 -- one-class SVM
	3 -- epsilon-SVR
	4 -- nu-SVR
-t kernel_type : set type of kernel function (default 2)
	0 -- linear: u'*v    (Can be okay for high numbers of features, is FASTER)
	1 -- polynomial: (gamma*u'*v + coef0)^degree
	2 -- radial basis function: exp(-gamma*|u-v|^2)   (Basically unbeatable)
	3 -- sigmoid: tanh(gamma*u'*v + coef0)
-d degree : set degree in kernel function (default 3)
-g gamma : set gamma in kernel function (default 1/num_features) TUNE****
-r coef0 : set coef0 in kernel function (default 0)
-c cost : set the parameter C of C-SVC, epsilon-SVR, and nu-SVR (default 1) TUNE****
-n nu : set the parameter nu of nu-SVC, one-class SVM, and nu-SVR (default 0.5)
-p epsilon : set the epsilon in loss function of epsilon-SVR (default 0.1)
-m cachesize : set cache memory size in MB (default 100)
-e epsilon : set tolerance of termination criterion (default 0.001)
-h shrinking: whether to use the shrinking heuristics, 0 or 1 (default 1)
-b probability_estimates: whether to train a SVC or SVR model for probability estimates, 0 or 1 (default 0)  CAN TURN OFF TO TRAIN FASTER IN THE NEW VERSION
-wi weight: set the parameter C of class i to weight*C, for C-SVC (default 1)   NOT IMPLEMENTED BUT WORTH PLAYING WITH WHEN I DO


Learning Framework
==================

Genevieve Gorrell, June 2014
----------------------------

The learning framework, at the time of writing, wraps various Mallet classification algorithms as well as Mallet CRF sequence modelling and LibSVM. It implements two modes--classification, which simply assigns a class to the instance annotation, and named entity recognition, which creates entity annotations spanning the instances identified as being within the span of the NE. All algorithms are capable of being used in both modes. Evaluation modes simply return basic classification accuracy. Note that accuracy alone may give a misleading impression of task success for highly imbalanced datasets, such as are common in NLP. Suggested use of evaluation modes is to facilitate parameter tuning. GATE offers a range of more advanced evaluation functionality for determining actual task success.

Unlike the Batch Learning PR, the Learning Framework takes much more information in the form of runtime parameters, and much less is required to be included in the configuration file. The configuration file defines only the features to be used. An example is included alongside this README and a section below describes the feature spec in more detail.


Runtime Parameters
------------------

-classFeature--For classification, the class to be learned takes the form of a feature on the instance annotation. This should be specified here. In NER mode, this is ignored.

-classType--Indicates the annotation type from which the learning class should be taken. For classification, it is acceptable for the class to be taken from a different annotation type than the instance--the colocated classType annotation will be used. Often, however, this will just be the same as the instance type. For NER, this would indicate the annotation type, e.g. "Mention", that we are aiming to find.

-confidenceThreshold--for classification, this indicates the minimum confidence required from the model for us to create an annotation at application time. For NER, this indicates the minimum average confidence for the whole entity.

-featureSpecURL--indicates the location of the feature specification file. If you edit the actual content of the file then you need to reinitialize the PR for this change to register. There is a section below describing feature types.

-foldsForXVal--number of folds to use for cross-validation.

-identifierFeature--Mallet allows you to include an identifier feature in your training instances. It isn't used anywhere and it isn't terribly important.

-inputASName--contains instances, class annotations, sequence span annotations.

-instanceName--the annotation type to be used as training instance. Token is common.

-learnerParams--a free text field that some learners are able to make use of as parameters. See the section below for a specification of which learners take parameters and what they take.

-mode--NAMED_ENTITY_RECOGNITION or CLASSIFICATION

-operation:
 -TRAIN trains the model indicated in trainingAlgo (see below)
 -APPLY_CURRENT_MODEL applies whatever is found in the savedModel directory. It doesn't matter what is selected in trainingAlgo at apply time. It applies whatever it has.
 -EVALUATE_XFOLD--Cross-fold validation is performed and accuracy is returned.
 -EVALUATE_HOLDOUT--Hold-out validation is performed and accuracy is returned.
 -EXPORT_ARFF--implemented but not tested very thoroughly

-outputASName--Where to output the annotations to at application time.

-saveDirectory--indicates the location to which models should be saved.

-sequenceSpan--for sequence learning, the smallest meaningful sequence. Training instances will be constructed for the entire sequence, as required by Mallet. Ignored for non-sequence learners.

-trainingAlgo--indicates the algorithm to use for training.

-trainingProportion--the proportion of the data to use for training in holdout evaluation.


Feature Specification
---------------------

<ATTRIBUTE>
<TYPE>Token</TYPE>
<FEATURE>string</FEATURE>
<DATATYPE>nominal</DATATYPE>
</ATTRIBUTE>

Attribute elements take features from annotations colocated with the instance, or typically, from the instance itself. Type and feature specify the annotation type and feature to use. Datatype specifies if the attribute is nominal or numeric.

In the case that more than one annotation of the specified type colocates with the instance, the first is used. In the case that multiple colocated annotations begin at the same offset, it is unclear which would be selected.

<NGRAM>
<NUMBER>3</NUMBER>
<TYPE>Token</TYPE>
<FEATURE>category</FEATURE>
</NGRAM>

N-gram features create training attributes from features for all examples of the specified annotation type within the span of the instance. Number indicates the size of a sliding window across the attribute annotations within the instance. For a bag of words model you would simply specify "1", but larger n-grams can be used.

Only the nominal datatype is supported for n-gram features.

<ATTRIBUTELIST>
<TYPE>Token</TYPE>
<FEATURE>orth</FEATURE>
<DATATYPE>nominal</DATATYPE>
<FROM>-2</FROM>
<TO>3</TO>
</ATTRIBUTELIST>

Attribute list features create learning attributes from the specified feature for each annotation of the specified type in the range specified around the instance. For example, -5 to 5 creates a total of ten attributes (assuming they are available to be had).


Learner Parameters
------------------

LibSVM (Note that -wi is not currently supported):

-s svm_type : set type of SVM (default 0)
	0 -- C-SVC
	1 -- nu-SVC
	2 -- one-class SVM
	3 -- epsilon-SVR
	4 -- nu-SVR
-t kernel_type : set type of kernel function (default 2)
	0 -- linear: u'*v
	1 -- polynomial: (gamma*u'*v + coef0)^degree
	2 -- radial basis function: exp(-gamma*|u-v|^2)
	3 -- sigmoid: tanh(gamma*u'*v + coef0)
-d degree : set degree in kernel function (default 3)
-g gamma : set gamma in kernel function (default 1/num_features)
-r coef0 : set coef0 in kernel function (default 0)
-c cost : set the parameter C of C-SVC, epsilon-SVR, and nu-SVR (default 1)
-n nu : set the parameter nu of nu-SVC, one-class SVM, and nu-SVR (default 0.5)
-p epsilon : set the epsilon in loss function of epsilon-SVR (default 0.1)
-m cachesize : set cache memory size in MB (default 100)
-e epsilon : set tolerance of termination criterion (default 0.001)
-h shrinking: whether to use the shrinking heuristics, 0 or 1 (default 1)
-b probability_estimates: whether to train a SVC or SVR model for probability estimates, 0 or 1 (default 0)
-wi weight: set the parameter C of class i to weight*C, for C-SVC (default 1)

Mallet CRF: No parameters available.

Mallet classification:

Balanced Winnow: numbers in order seperated by space
 epsilon (double, default 0.5)
 delta (double, default 0.1)
 max iterations (int, default 30)
 cooling rate (double, default 0.5)

C45:
 max depth (int)

Decision Tree:
 max depth (int)
 
Max Ent GE Range, Max Ent GE, Max Ent PR: These all take an array of constraints. This isn't currently supported.

Max Ent:
 gaussian prior (double, a parameter to avoid overtraining. 1.0 is the default value.)
 max iterations (int. I have coded this in but it is possible that Mallet still doesn't use it.)

MC Max Ent:
 The following configurations only are supported:
  gaussianPriorVariance (double, a parameter to avoid overtraining)
 OR
  gaussianPriorVariance (double)
  useMultiConditionalTraining (boolean)
 OR
  hyperbolicPriorSlope (double)
  hyperbolicPriorSharpness (double)
 OR no arguments.

Naive Bayes, Naive Bayes EM: These don't take any parameters.

Winnow:
 a (double)
 b (double)
 nfact (double, optional)


