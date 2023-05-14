# New git project

## clone

```bash
git clone https://github.com/amartinm7/openbk.git
```

…or create a new repository on the command line

```bash
echo "# openbk" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/amartinm7/openbk.git
git push -u origin main
```

…or push an existing repository from the command line

```bash
git remote add origin https://github.com/amartinm7/openbk.git
git branch -M main
git push -u origin main
```

## setup java version to use springboot 3

with the `sdk` tool, list the installed versions

```bash
sdk list java
sdk install java 17.0.6-amzn
sdk default java 19.0.2-amzn

# temporally only works in the current term 
sdk use java 17.0.6-amzn 

sdk uninstall java 17.0.6-amzn
sdk current java
sdk current
ls -al ~/.sdkman/candidates/java/
```
