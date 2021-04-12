# Propose pull request for github project
This is short summary on how to propose pull request to 3-rd party project on github.

## Environment setup
* Fork target repository on github.
* Clone forked repository.
  ```git clone https://github.com/jveverka/the-project.git```
* Add remote pointing to source repository.
  ```git add remote upstream https://github.com/TheRepository/the-project.git```

## Propose pull request procedure
* Switch to branch which you want to propose pull request.
  ```git checkout master```
* Update master branch from remote source and push it to your forked repo.
  ```
  git pull upstream master
  git push origin master
  ``` 
* Create new branch just for this pull request.
  ```git checkout -b master_fix-issue-name``` 
* Make changes and commit them locally.
  ```
  git add .
  git commit -a -m "my commit message"
  ``` 
* Push commit into new branch to your forked repository.
  ```git push origin master_fix-issue-name```
* On github web page: create pull request for target repository.
* When your pull request is merged in original repo, or if you want to propose new pull request, follow same steps. 
  always create new branch for each pull request.


