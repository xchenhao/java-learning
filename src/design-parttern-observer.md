### 观察者模式

原理：
观察者模式定义：简单地说，观察者模式定义了一个一对多的依赖关系，让一个或多个观察者对象监察一个主题对象。
这样一个主题对象在状态上的变化能够通知所有的依赖于此对象的那些观察者对象，使这些观察者对象能够自动更新。

依赖于抽象，而不依赖于具体的实现

实现：
四个角色
- Subject（被观察的对象接口）
    规定 ConcreteSubject 的统一接口；
    每个 Subject 可以有多个 Observer
- ConcreteSubject（具体被观察对象）
    维护对所有具体观察者的引用的列表；
    状态发生变化时会发送通知给所有注册的观察者
- Observer（观者者接口）
    规定 ConcreteObserver 的统一接口；
    定义了一个 update() 方法，
    在被观察对象状态改变时被调用
- ConcreteObserver（具体观察者）
    维护一个对 ConcreteSubject 的引用；
    特定状态与 ConcreteSubject 同步；
    实现 Observer 接口，通过 update() 方法接收 ConcreteSubject 的通知

    
 作用：
 观察者模式在被观察者和观察者之间建立一个抽象的耦合。被观察者角色所知道的只是一个具体观察者列表。
 由于被观察者和观察者没有紧密地耦合在一起，因此它们可以属于不同的抽象化层次。如果被
 观察者和观察者都被扔到一起，那么这个对象必然跨越抽象化和具体化层次。
 观察者模式支持广播通讯。被观察者会向所有的登记过的观察者发出通知。


