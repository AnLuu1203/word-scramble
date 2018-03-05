# word-scramble

## Require
- Java 1.8
- MySQL (install: https://linode.com/docs/databases/mysql/install-mysql-on-ubuntu-14-04/)
- sbt (install: https://www.scala-sbt.org/download.html)

## Usage

Clone:
```
git clone git@github.com:luuthienan/word-scramble.git
```

Mount to project:
```
cd word-scramble
```

Run SQL Script:
```
mysql -u your_username -p < app/assets/english_dictionary.sql
```

Edit username and password your DB in `conf/application.conf`

Run:
```
sbt run
```

Go to http://localhost:9000

## References
- Play framework: https://www.playframework.com/
- Ebean ORM: http://ebean-orm.github.io/
- English Dictionary Database: https://sourceforge.net/projects/mysqlenglishdictionary/
