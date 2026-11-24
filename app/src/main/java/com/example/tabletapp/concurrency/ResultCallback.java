package com.example.tabletapp.concurrency;

// T is a placeholder for the data returned
public interface ResultCallback<T> {

    void onSuccess(T result);

    void onError(String message);
}
