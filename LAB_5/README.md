# JMeter LAB

## Application Description

I chose guidePRO application which is app for my bachelor project on PJATK built in group of four students.
Purpose of this app is to enable guides create and plan tours and to help users like tourists find their dream tour.

The endpoint which will be used during load tests is: /users/all
GuidePRO team ensures that 100 logged users could use this app at the same time.

## Load tests

### Getting to know with Jmeter tool

To start I have prepared Thread, Sampler and Listeners like View Results Tree, Summarize Report and Aggregate Graph.
!JMETER_start
![ga_1.png](https://github.com/s23577/TAU/blob/main/LAB_3/images/ga_1.png?raw=true)

### First cases in JMeter

#### 1 thread, 1 rump-up period, 1 loop count

!test_1
For one request the time of execution is 11 ms.
Important is, that when I run this test plan more than once - the time is between 7-39 ms.

#### 100 threads, 1 rump-up period, 1 loop count

!test_2
The average time is 4 ms. Server has no issue with 100 users (threads).

!test_2_more
The throughput equals about 6k requests per 1 minute.

#### 100 threads, 1 rump-up period, 100 loop count

!test_3
The average time has picked up to 146 ms. Total samples which were send equals 10000.

!test_3_more
The amount of requests per minute equals now 40k.

#### 10000 threads, 1 rump-up period, 10 loop count

!test_4
Test plan is not finished. It seems that tests loaded app server. Total expected samples is 100000, but it stopped at
40510 and never started again.
The tool JMeter is also not corresponding properly at such conditions.
For 1000 threads - tests are performed under 10s.
I have found that over 4k requests - the app or server crashes.

#### 4000 threads, 10 rump-up period, 1 loop count

!test_5
The longest request response for 4000 users at the same time equals about 5s. The average is 1s which is still very good
performance even if guidePRO assumes only 100 active users.

## Main conclusions

JMeter tool is very useful to do performance and load tests. Developer or tester can check app behavior in different
conditions like: a lot of request sent at the same time.
This tool allow developers to simulate various user scenarios and analyze system behavior under different loads.
Additionally, JMeter's flexibility enables its integration into continuous integration and delivery pipelines,
facilitating automated testing processes and ensuring consistent application performance across development cycles.
JMeter GUI looks old and I needed to stop the app, because main STOP button didn't react - so this tool has also some
disadvantages of usage.