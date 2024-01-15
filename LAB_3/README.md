# GitHub Actions results

## 1. some builds
![ga_1.png](https://github.com/s23577/TAU/blob/main/LAB_3/images/ga_1.png?raw=true)  
![ga_2.png](https://github.com/s23577/TAU/blob/main/LAB_3/images/ga_2.png?raw=true)  
![ga_3.png](https://github.com/s23577/TAU/blob/main/LAB_3/images/ga_3.png?raw=true)  

## 2. tests result from GitHub Actions
![results.png](https://github.com/s23577/TAU/blob/main/LAB_3/images/results.png?raw=true)

## 3. maven.yml file

```yaml
name: Java CI with Maven

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 21
      uses: actions/setup-java@v3
      with:
        java-version: '21'
        distribution: 'temurin'
        cache: maven
    - name: Unit Tests with Maven
      working-directory: ./LAB_3
      run: mvn test
```




