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

import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import rx.Observable;

import static fr.javatic.mongo.rxadapter.Utils.resultHandler;
import static fr.javatic.mongo.rxadapter.Utils.voidResultHandler;

public class RxMongoDatabase {
    private final MongoDatabase delegate;

    public RxMongoDatabase(MongoDatabase delegate) {
        this.delegate = delegate;
    }

    public String getName() {
        return delegate.getName();
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

    public RxMongoDatabase withCodecRegistry(CodecRegistry codecRegistry) {
        return new RxMongoDatabase(delegate.withCodecRegistry(codecRegistry));
    }

    public RxMongoDatabase withReadPreference(ReadPreference readPreference) {
        return new RxMongoDatabase(delegate.withReadPreference(readPreference));
    }

    public RxMongoDatabase withWriteConcern(WriteConcern writeConcern) {
        return new RxMongoDatabase(delegate.withWriteConcern(writeConcern));
    }

    public RxMongoCollection<Document> getCollection(String collectionName) {
        return new RxMongoCollection<>(delegate.getCollection(collectionName));
    }

    public <TDocument> RxMongoCollection<TDocument> getCollection(String collectionName,
                                                                  Class<TDocument> documentClass) {
        return new RxMongoCollection<>(delegate.getCollection(collectionName, documentClass));
    }

    public Observable<Document> executeCommand(Bson command) {
        return Observable.create(subscriber -> delegate.executeCommand(command, resultHandler(subscriber)));
    }

    public Observable<Document> executeCommand(Bson command, ReadPreference readPreference) {
        return Observable.create(subscriber -> delegate.executeCommand(command,
            readPreference,
            resultHandler(subscriber)));
    }

    public <TResult> Observable<TResult> executeCommand(Bson command,
                                                        Class<TResult> resultClass) {
        return Observable.create(subscriber -> delegate.executeCommand(command,
            resultClass,
            resultHandler(subscriber)));
    }

    public <TResult> Observable<TResult> executeCommand(Bson command,
                                                        ReadPreference readPreference,
                                                        Class<TResult> resultClass,
                                                        SingleResultCallback<TResult> callback) {
        return Observable.create(subscriber -> delegate.executeCommand(command,
            readPreference,
            resultClass,
            resultHandler(subscriber)));
    }

    public Observable<Void> dropDatabase() {
        return Observable.create(subscriber -> delegate.dropDatabase(voidResultHandler(subscriber, null)));
    }

    public RxMongoIterable<String> listCollectionNames() {
        return new RxMongoIterable<>(delegate.listCollectionNames());
    }

    public RxListCollectionsIterable<Document> listCollections() {
        return new RxListCollectionsIterable<>(delegate.listCollections());
    }

    public <TResult> RxListCollectionsIterable<TResult> listCollections(Class<TResult> resultClass) {
        return new RxListCollectionsIterable<>(delegate.listCollections(resultClass));
    }

    public Observable<String> createCollection(String collectionName) {
        return Observable.create(subscriber -> delegate.createCollection(collectionName,
            voidResultHandler(subscriber, collectionName)));
    }

    public Observable<String> createCollection(String collectionName,
                                             CreateCollectionOptions options,
                                             SingleResultCallback<Void> callback) {
        return Observable.create(subscriber -> delegate.createCollection(collectionName, options, voidResultHandler(
            subscriber, collectionName)));
    }
}
