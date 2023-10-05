# rtk-it

## Задание

Необходимо разработать консольную программу для добавления текста к картинкам (например, чтобы создать мем). Программа
должна поддерживать следующие команды:

1) help – выводит мануал по использованию программы, который содержит описание всех команд и их возможных аргументов
2) mem – добавляет к указанной картинке заданный текст. Например “java -jar application.jar mem ./picture.png ‘hello
   world’” – данная команда добавляет текст «hello world» к картинке picture.png, которая находится в текущей директории

## Запуск

1. ``mvn package``

2. ```
   java -jar rtk-it-1.0.jar <аргументы>
   ```

## Команды

1. **help** — выводит информацию о программе
2. **mem (path) (text) (color) (position)** — добавляет текст к картинке  
   path - путь к файлу  
   text - текст для добавления  
   color - rgb-цвет в формате числа, необязательный параметр  
   position - расположение текста (top, center или bottom), необязательный параметр