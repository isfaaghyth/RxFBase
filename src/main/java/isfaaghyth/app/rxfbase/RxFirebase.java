package isfaaghyth.app.rxfbase;

import android.support.annotation.NonNull;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

/**
 * Created by isfaaghyth on 8/14/17.
 * github: @isfaaghyth
 */

public class RxFirebase {

    public static Observable<Boolean> setValue(final DatabaseReference ref, final Object obj) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override public void call(Subscriber<? super Boolean> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    try {
                        ref.setValue(obj);
                        subscriber.onNext(true);
                        subscriber.onCompleted();
                    } catch (Exception e) {
                        subscriber.onNext(false);
                    }
                }
            }
        });
    }

    @NonNull public static Observable<DataSnapshot> singleValue(final Query query) {
        return Observable.create(new Observable.OnSubscribe<DataSnapshot>() {
            @Override public void call(final Subscriber<? super DataSnapshot> subscriber) {
                final ValueEventListener valueEvent = query.addValueEventListener(new ValueEventListener() {
                    @Override public void onDataChange(DataSnapshot dataSnapshot) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(dataSnapshot);
                        }
                    }
                    @Override public void onCancelled(DatabaseError databaseError) {
                        subscriber.onError(databaseError.toException());
                    }
                });
                subscriber.add(Subscriptions.create(new Action0() {
                    @Override public void call() {
                        query.removeEventListener(valueEvent);
                    }
                }));
            }
        });
    }

    @NonNull public Observable<DataSnapshot> childValue(final Query query) {
        return Observable.create(new Observable.OnSubscribe<DataSnapshot>() {
            @Override public void call(final Subscriber<? super DataSnapshot> subscriber) {
                final ChildEventListener childEvent = query.addChildEventListener(new ChildEventListener() {
                    @Override public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(dataSnapshot);
                        }
                    }
                    @Override public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(dataSnapshot);
                        }
                    }
                    @Override public void onChildRemoved(DataSnapshot dataSnapshot) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(dataSnapshot);
                        }
                    }
                    @Override public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(dataSnapshot);
                        }
                    }
                    @Override public void onCancelled(DatabaseError databaseError) {
                        subscriber.onError(databaseError.toException());
                    }
                });
                subscriber.add(Subscriptions.create(new Action0() {
                    @Override public void call() {
                        query.removeEventListener(childEvent);
                    }
                }));
            }
        });
    }
}
