/*
 * Copyright 2015 Yann Le Moigne
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.javatic.mongo.rxadapter;

import com.mongodb.MongoNamespace;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import rx.Observable;

import java.util.List;

import static fr.javatic.mongo.rxadapter.Utils.resultHandler;
import static fr.javatic.mongo.rxadapter.Utils.voidResultHandler;

public class RxMongoCollection<T> {
    private final MongoCollection<T> delegate;

    public RxMongoCollection(MongoCollection<T> delegate) {
        this.delegate = delegate;
    }

    public MongoNamespace getNamespace() {
        return delegate.getNamespace();
    }

    public Class<T> getDocumentClass() {
        return delegate.getDocumentClass();
    }

    public CodecRegistry getCodecRegistry() {
        return delegate.getCodecRegistry();
    }

    public ReadPreference getReadPreference() {
        return delegate.getReadPreference();
    }

    public WriteConcern getWriteConcern() {
        return delegate.getWriteConcern();
    }

    public <NewTDocument> RxMongoCollection<NewTDocument> withDocumentClass(Class<NewTDocument> newDocumentClass) {
        return new RxMongoCollection<>(delegate.withDocumentClass(newDocumentClass));
    }

    public RxMongoCollection<T> withCodecRegistry(CodecRegistry codecRegistry) {
        return new RxMongoCollection<>(delegate.withCodecRegistry(codecRegistry));
    }

    public RxMongoCollection<T> withReadPreference(ReadPreference readPreference) {
        return new RxMongoCollection<>(delegate.withReadPreference(readPreference));
    }

    public RxMongoCollection<T> withWriteConcern(WriteConcern writeConcern) {
        return new RxMongoCollection<>(delegate.withWriteConcern(writeConcern));
    }

    public Observable<Long> count() {
        return Observable.create(subscriber -> delegate.count(resultHandler(subscriber)));
    }

    public Observable<Long> count(Bson filter) {
        return Observable.create(subscriber -> delegate.count(filter, resultHandler(subscriber)));
    }

    public Observable<Long> count(Bson filter, CountOptions options) {
        return Observable.create(subscriber -> delegate.count(filter, options, resultHandler(subscriber)));
    }

    public <TResult> RxDistinctIterable<TResult> distinct(String fieldName, Class<TResult> resultClass) {
        return new RxDistinctIterable<>(delegate.distinct(fieldName, resultClass));
    }

    public RxFindIterable<T> find() {
        return new RxFindIterable<>(delegate.find());
    }

    public <TResult> RxFindIterable<TResult> find(Class<TResult> resultClass) {
        return new RxFindIterable<>(delegate.find(resultClass));
    }

    public RxFindIterable<T> find(Bson filter) {
        return new RxFindIterable<>(delegate.find(filter));
    }

    public <TResult> RxFindIterable<TResult> find(Bson filter, Class<TResult> resultClass) {
        return new RxFindIterable<>(delegate.find(filter, resultClass));
    }

    public RxAggregateIterable<T> aggregate(List<? extends Bson> pipeline) {
        return new RxAggregateIterable<>(delegate.aggregate(pipeline));
    }

    public <TResult> RxAggregateIterable<TResult> aggregate(List<? extends Bson> pipeline, Class<TResult> resultClass) {
        return new RxAggregateIterable<>(delegate.aggregate(pipeline, resultClass));
    }

    public RxMapReduceIterable<T> mapReduce(String mapFunction, String reduceFunction) {
        return new RxMapReduceIterable<>(delegate.mapReduce(mapFunction, reduceFunction));
    }

    public <TResult> RxMapReduceIterable<TResult> mapReduce(String mapFunction,
                                                            String reduceFunction,
                                                            Class<TResult> resultClass) {
        return new RxMapReduceIterable<>(delegate.mapReduce(mapFunction, reduceFunction, resultClass));
    }

    public Observable<BulkWriteResult> bulkWrite(List<? extends WriteModel<? extends T>> requests) {
        return Observable.create(subscriber -> delegate.bulkWrite(requests, resultHandler(subscriber)));
    }

    public Observable<BulkWriteResult> bulkWrite(List<? extends WriteModel<? extends T>> requests,
                                                 BulkWriteOptions options) {
        return Observable.create(subscriber -> delegate.bulkWrite(requests, options, resultHandler(
            subscriber)));
    }

    public Observable<T> insertOne(T t) {
        return Observable.create(subscriber -> delegate.insertOne(t,
            voidResultHandler(subscriber, t)));
    }

    public Observable<List<? extends T>> insertMany(List<? extends T> ts) {
        return Observable.create(subscriber -> delegate.insertMany(ts,
            voidResultHandler(subscriber, ts)));
    }

    public Observable<List<? extends T>> insertMany(List<? extends T> ts,
                                       InsertManyOptions options) {
        return Observable.create(subscriber -> delegate.insertMany(ts,
            voidResultHandler(subscriber, ts)));
    }

    public Observable<DeleteResult> deleteOne(Bson filter) {
        return Observable.create(subscriber -> delegate.deleteOne(filter,
            resultHandler(subscriber)));
    }

    public Observable<DeleteResult> deleteMany(Bson filter) {
        return Observable.create(subscriber -> delegate.deleteMany(filter,
            resultHandler(subscriber)));
    }

    public Observable<UpdateResult> replaceOne(Bson filter,
                                               T replacement) {
        return Observable.create(subscriber -> delegate.replaceOne(filter, replacement,
            resultHandler(subscriber)));
    }

    public Observable<UpdateResult> replaceOne(Bson filter,
                                               T replacement,
                                               UpdateOptions options) {
        return Observable.create(subscriber -> delegate.replaceOne(filter, replacement, options,
            resultHandler(subscriber)));
    }

    public Observable<UpdateResult> updateOne(Bson filter, Bson update) {
        return Observable.create(subscriber -> delegate.updateOne(filter, update,
            resultHandler(subscriber)));
    }

    public Observable<UpdateResult> updateOne(Bson filter,
                                              Bson update,
                                              UpdateOptions options) {
        return Observable.create(subscriber -> delegate.updateOne(filter, update, options,
            resultHandler(subscriber)));
    }

    public Observable<UpdateResult> updateMany(Bson filter, Bson update) {
        return Observable.create(subscriber -> delegate.updateMany(filter, update,
            resultHandler(subscriber)));
    }

    public Observable<UpdateResult> updateMany(Bson filter,
                                               Bson update,
                                               UpdateOptions options) {
        return Observable.create(subscriber -> delegate.updateMany(filter, update,
            resultHandler(subscriber)));
    }

    public Observable<T> findOneAndDelete(Bson filter) {
        return Observable.create(subscriber -> delegate.findOneAndDelete(filter,
            resultHandler(subscriber)));
    }

    public Observable<T> findOneAndDelete(Bson filter,
                                          FindOneAndDeleteOptions options) {
        return Observable.create(subscriber -> delegate.findOneAndDelete(filter, options,
            resultHandler(subscriber)));
    }

    public Observable<T> findOneAndReplace(Bson filter, T replacement) {
        return Observable.create(subscriber -> delegate.findOneAndReplace(filter, replacement,
            resultHandler(subscriber)));
    }

    public Observable<T> findOneAndReplace(Bson filter,
                                           T replacement,
                                           FindOneAndReplaceOptions options) {
        return Observable.create(subscriber -> delegate.findOneAndReplace(filter, replacement, options,
            resultHandler(subscriber)));
    }

    public Observable<T> findOneAndUpdate(Bson filter, Bson update) {
        return Observable.create(subscriber -> delegate.findOneAndUpdate(filter, update,
            resultHandler(subscriber)));
    }

    public Observable<T> findOneAndUpdate(Bson filter,
                                          Bson update,
                                          FindOneAndUpdateOptions options) {
        return Observable.create(subscriber -> delegate.findOneAndUpdate(filter, update, options,
            resultHandler(subscriber)));
    }

    public Observable<Void> dropCollection() {
        return Observable.create(subscriber -> delegate.dropCollection(
            voidResultHandler(subscriber, null)));
    }

    public Observable<Bson> createIndex(Bson key) {
        return Observable.create(subscriber -> delegate.createIndex(key,
            voidResultHandler(subscriber, key)));
    }

    public Observable<Bson> createIndex(Bson key, CreateIndexOptions options) {
        return Observable.create(subscriber -> delegate.createIndex(key, options,
            voidResultHandler(subscriber, key)));
    }

    public RxListIndexesIterable<Document> listIndexes() {
        return new RxListIndexesIterable<>(delegate.listIndexes());
    }

    public <TResult> RxListIndexesIterable<TResult> listIndexes(Class<TResult> resultClass) {
        return new RxListIndexesIterable<>(delegate.listIndexes(resultClass));
    }

    public Observable<String> dropIndex(String indexName) {
        return Observable.create(subscriber -> delegate.dropIndex(indexName,
            voidResultHandler(subscriber, indexName)));
    }

    public Observable<Void> dropIndexes() {
        return Observable.create(subscriber -> delegate.dropIndexes(
            voidResultHandler(subscriber, null)));
    }

    public Observable<MongoNamespace> renameCollection(MongoNamespace newCollectionNamespace) {
        return Observable.create(subscriber -> delegate.renameCollection(newCollectionNamespace,
            voidResultHandler(subscriber, newCollectionNamespace)));
    }

    public Observable<MongoNamespace> renameCollection(MongoNamespace newCollectionNamespace,
                                             RenameCollectionOptions options) {
        return Observable.create(subscriber -> delegate.renameCollection(newCollectionNamespace, options,
            voidResultHandler(subscriber, newCollectionNamespace)));
    }


}
