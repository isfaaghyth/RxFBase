# RxFBase
wrapping firebase with rx

Yesterday, I have a problem when doing real-time mapping (maps) using firebase. So, I try to create this library for my case. enjoy!

TODO:
- [x] firebase database
- [ ] firebase auth
- [ ] firebase storage

## Installation
Add it in your root build.gradle at the end of repositories:

```
allprojects {
   repositories {
      ...
      maven { url 'https://jitpack.io' }
   }
}
```

Add the dependency

```
compile 'com.github.isfaaghyth:RxFBase:1.0.0'
```
## Usage
set value:
```java
HashMap<String, String> data = new HashMap<>();
data.put("title", "this is title");
data.put("message", "awesome message!");
RxFirebase.setValue(dbRef, data)
          .subscribe(isSuccess -> {
            //isSuccess true/false
          });
```

for singleValue:
```java
RxFirebase.singleValue(dbRef)
          .subscribe(dataSnapshot -> {
            //do something!
          }, throwable -> {
            //ops!
          });
```
for childValue:
```java
RxFirebase.childValue(dbRef)
          .subscribe(dataSnapshot -> {
            //do something!
          }, throwable -> {
            //ops!
          });
```

enjoy!


MIT
