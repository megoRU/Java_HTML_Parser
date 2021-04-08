[![Build Status](https://travis-ci.com/megoRU/ficbook.net_Parser.svg?branch=master)](https://travis-ci.com/megoRU/ficbook.net_Parser) ![GitHub All Releases](https://img.shields.io/github/downloads/megoRU/ficbook.net_Parser/total)

# Java ficbook.net Parser

## Что и как работает

Программа сохраняет главу в `txt` файл с названием книги на рабочий стол. <br>
Программа поддерживает различные ссылки:
1. https://ficbook.net/readfic/9975473/25667941#part_content
2. https://ficbook.net/readfic/9975473/25667941
3. и т.д

![Image description](app.png)

## Установка компонентов для работы программы

#### Windows
• На Windows установите [Oracle JDK 15+](https://www.oracle.com/java/technologies/javase-downloads.html) или [OpenJDK 15+](https://adoptopenjdk.net/). <br>
• На Windows установите [Python 3.9+](https://www.python.org/downloads/). <br>

#### Ubuntu

**Terminal:**<br>
`sudo apt install openjdk-15-jre` <br>
`sudo apt install python3-pip` <br>
`pip install cloudscraper` <br>

Щелкните правой кнопкой мыши на файле, выберите пункт Свойства, затем перейдите на вкладку
`Permissions` и установите флажок "Allow executing this file as a program".

#### Mac OS
**Тесты не проводились**

## Запуск программы

Windows/Linux*<br>
Двойным нажатием по `.jar` файлу. Файл с расширением `.py` должен находиться в одной директории с `.jar`

*Linux<br>
Щелкните правой кнопкой мыши на файле, выберите пункт Свойства, затем перейдите на вкладку
`Permissions` и установите флажок напротив `Allow executing this file as a program`.

## FAQ & Troubleshooting

Вопрос: После нажатия кнопки `Отправить` ничего не происходит. <br>
Ответ: Попробуйте еще раз. Если не помогло, тогда стоит обновить скрипт парсера: `pip install cloudscraper` <br>

Вопрос: Не открывается программа по двойному клику. <br>
Ответ: Если Вы используете `OpenJDK` и у Вас не открывается программа по двойному клику. Вам нужно скачать и запустить сначало это: [Jarfix 3.0.0](https://www.heise.de/download/product/jarfix-41657/download)

## LICENSE
#### [CC BY-NC-SA 4.0](https://github.com/megoRU/ficbook.net_Parser/blob/master/LICENSE)

## Copyright Notice

1.  This program uses the library: [cloudscraper](https://github.com/VeNoMouS/cloudscraper)