# cluster-console

Sample project illustrating combining D3 with [scalajs-react](https://github.com/japgolly/scalajs-react)  .

This project provides simple views for akka cluster topography.

This code is heavily influenced and owes its existence to: https://github.com/ochrons/scalajs-spa-tutorial.


###Getting started

1) open 2 terminals.
2) in first terminal:

     sbt
     re-start
     
3) in second terminal:

    sbt
    ~fastOptJS
    
    
    
#### Sample Cluster
    
    
To start the sample cluster:
    

    sbt 'project sampleCluster' 'dist'
    
    cd sampleCluster/target/universal
    unzip samplecluster-1.0.0.zip 
    sudo chmod +x samplecluster-1.0.0/bin/samplecluster
    
    samplecluster-1.0.0/bin/samplecluster 127.0.0.1 2551 FooCluster 127.0.0.1:2551 Stable-Seed &
    samplecluster-1.0.0/bin/samplecluster 127.0.0.1 2552 FooCluster 127.0.0.1:2551 Baz-Security &
    samplecluster-1.0.0/bin/samplecluster 127.0.0.1 2553 FooCluster 127.0.0.1:2551 Baz-Security &
    samplecluster-1.0.0/bin/samplecluster 127.0.0.1 2554 FooCluster 127.0.0.1:2551 Foo-Worker &
    samplecluster-1.0.0/bin/samplecluster 127.0.0.1 2555 FooCluster 127.0.0.1:2551 Foo-Worker &
    samplecluster-1.0.0/bin/samplecluster 127.0.0.1 2556 FooCluster 127.0.0.1:2551 Bar-Worker &
    samplecluster-1.0.0/bin/samplecluster 127.0.0.1 2557 FooCluster 127.0.0.1:2551 Bar-Worker &
    samplecluster-1.0.0/bin/samplecluster 127.0.0.1 2558 FooCluster 127.0.0.1:2551 Foo-Http &
    samplecluster-1.0.0/bin/samplecluster 127.0.0.1 2559 FooCluster 127.0.0.1:2551 Bar-Http &
    

    
    samplecluster-1.0.0/bin/samplecluster 127.0.0.1 2661 BazCluster 127.0.0.1:2661 Stable-Seed &
    samplecluster-1.0.0/bin/samplecluster 127.0.0.1 2662 BazCluster 127.0.0.1:2661 Baz-Security &
    samplecluster-1.0.0/bin/samplecluster 127.0.0.1 2663 BazCluster 127.0.0.1:2661 Foo-Worker &
    samplecluster-1.0.0/bin/samplecluster 127.0.0.1 2664 BazCluster 127.0.0.1:2661 Bar-Worker &
    samplecluster-1.0.0/bin/samplecluster 127.0.0.1 2665 BazCluster 127.0.0.1:2661 Foo-Http &
    samplecluster-1.0.0/bin/samplecluster 127.0.0.1 2666 BazCluster 127.0.0.1:2661 Bar-Http &
    
    
    

To stop a particular actor by port:    

OSX:    
    
    kill -9 `lsof -i tcp:2551 | grep -i LISTEN`
    kill -9 `lsof -i tcp:2552 | grep -i LISTEN`
    kill -9 `lsof -i tcp:2553 | grep -i LISTEN`
    kill -9 `lsof -i tcp:2554 | grep -i LISTEN`
    kill -9 `lsof -i tcp:2555 | grep -i LISTEN`
    kill -9 `lsof -i tcp:2556 | grep -i LISTEN`
    kill -9 `lsof -i tcp:2557 | grep -i LISTEN`
    kill -9 `lsof -i tcp:2558 | grep -i LISTEN`
    kill -9 `lsof -i tcp:2559 | grep -i LISTEN`
     
    kill -9 `lsof -i tcp:2661 | grep -i LISTEN`
    kill -9 `lsof -i tcp:2662 | grep -i LISTEN`
    kill -9 `lsof -i tcp:2663 | grep -i LISTEN`
    kill -9 `lsof -i tcp:2664 | grep -i LISTEN`
    kill -9 `lsof -i tcp:2665 | grep -i LISTEN`
    kill -9 `lsof -i tcp:2666 | grep -i LISTEN`
     

Nix:

    fuser -k -n tcp 2551

etc.


#### multiple VM cluster: using the Vagrantfile

you'll need this box: https://github.com/dsugden/vagrant-ansible-ubuntu-oracle-java8, install it then

    vagrant up



Log in to each VM:

    vagrant ssh seed
    vagrant ssh member_2
    vagrant ssh member_3
    vagrant ssh member_4

then, on each

    sudo apt-get install unzip
    cp /vagrant/sampleCluster/target/universal/samplecluster-1.0.0.zip .
    unzip samplecluster-1.0.0.zip
    sudo chmod +x samplecluster-1.0.0/bin/samplecluster
    

seed

    samplecluster-1.0.0/bin/samplecluster 192.168.11.20 2551 FooCluster 192.168.11.20:2551 Stable-Seed &

    
member_2
    
    samplecluster-1.0.0/bin/samplecluster 192.168.11.22 2552 FooCluster 192.168.11.20:2551 Baz-Security &
    samplecluster-1.0.0/bin/samplecluster 192.168.11.22 2553 FooCluster 192.168.11.20:2551 Baz-Security &
    samplecluster-1.0.0/bin/samplecluster 192.168.11.22 2554 FooCluster 192.168.11.20:2551 Foo-Worker &
    
    
member_3
    
    samplecluster-1.0.0/bin/samplecluster 192.168.11.23 2555 FooCluster 192.168.11.20:2551 Foo-Worker &
    samplecluster-1.0.0/bin/samplecluster 192.168.11.23 2556 FooCluster 192.168.11.20:2551 Bar-Worker &
    samplecluster-1.0.0/bin/samplecluster 192.168.11.23 2557 FooCluster 192.168.11.20:2551 Bar-Worker &
    
member_4    

    samplecluster-1.0.0/bin/samplecluster 192.168.11.24 2558 FooCluster 192.168.11.20:2551 Foo-Http &
    samplecluster-1.0.0/bin/samplecluster 192.168.11.24 2559 FooCluster 192.168.11.20:2551 Bar-Http &
