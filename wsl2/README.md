# WSL2 - Windows subsystem for Linux 2

* [How to Install WSL 2 on Windows 10 May 2020 Update](https://www.omgubuntu.co.uk/how-to-install-wsl2-on-windows-10)
* [Windows Terminal](https://docs.microsoft.com/en-us/windows/terminal/) 

```
wsl --list --verbose
wsl --set-default-version 2
wsl --set-default <DistroName>  # set default distro 
wsl --terminate <DistroName>    # shutdown distro and start automatically.
```

### File system
```
\\wsl$
```