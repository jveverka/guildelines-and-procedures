# Propose pull request for github project
This is short summary on how to propose pull request to 3-rd party project on github.

## Environment setup
* Fork target repository on github.
* Clone forked repository.
  ```git clone https://github.com/jveverka/lighty-core.git```
* Add remote pointing to source repository.
  ```git add remote pantheon https://github.com/PantheonTechnologies/lighty-core.git```

## Propose pull request procedure
* Switch to brach which you want to propose pull request.
  ```git checkout master```
* Update master branch from remote source and push it to your forked repo.
  ```
  git pull pantheon master
  git push origin master
  ``` 
* Create new branch just for this pull request.
  ```git checkout -b master_fix-issue-name``` 
* Make chenges and commit them locally.
  ```
  git add .
  git commit -a -m "my commit message"
  ``` 
* Push commit into new branch to your forked repository.
  ```git push origin master_fix-issue-name```
* On github web page: create pull request for target repository.
* When your pull request is merged in original repo, or if you want to propose new pull request, follow same steps. 
  always create new branch for each pull request.


