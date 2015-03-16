package fr.javatic.mongo.rxadapter;

import com.mongodb.async.SingleResultCallback;
import rx.Subscriber;

class Utils {
    private Utils() {
    }

    public static <T> SingleResultCallback<T> resultHandler(Subscriber<? super T> subscriber) {
        return (success, error) -> {
            if (error != null) {
                subscriber.onError(error);
            } else {
                subscriber.onNext(success);
                subscriber.onCompleted();
            }
        };
    }

    public static SingleResultCallback<Void> voidResultHandler(Subscriber<?> subscriber) {
        return (success, error) -> {
            if (error != null) {
                subscriber.onError(error);
            } else {
                subscriber.onNext(null);
                subscriber.onCompleted();
            }
        };
    }
}
