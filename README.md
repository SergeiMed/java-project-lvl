# Генератор отличий конфигурационных файлов

Утилита запускается из терминала и сравнивает 2 конфигурационных файла в форматах YML, YAML, JSON. 
Показывает разницу в файлах в трех форматах: plain, stylish, json. 

## Использование:

```
./build/install/app/bin/app -h 
Usage: gendiff [-hV] [-f=format] filepath1 filepath2 
Compares two configuration files and shows a difference. 
      filepath1 path to first file 
      filepath2 path to second file 
-f, --format=format output format [default: stylish] 
-h, --help Show this help message and exit. 
-V, --version Print version information and exit.
```

Вывод различий в терминале в формате по умолчанию:
```
./build/install/app/bin/app file.json file1.json
{
    chars1: [a, b, c]
  - chars2: [d, e, f]
  + chars2: false
  - checked: false
  + checked: true
  - default: null
  + default: [value1, value2]
  - id: 45
  + id: null
  - key1: value1
  + key2: value2
    numbers1: [1, 2, 3, 4]
  - numbers2: [2, 3, 4, 5]
  + numbers2: [22, 33, 44, 55]
  - numbers3: [3, 4, 5]
  + numbers4: [4, 5, 6]
  + obj1: {nestedKey=value, isNested=true}
  - setting1: Some value
  + setting1: Another value
  - setting2: 200
  + setting2: 300
  - setting3: true
  + setting3: none
}
```

В формате plain:
```
./build/install/app/bin/app -f plain file.json file1.json
Property 'chars2' was updated. From [complex value] to false
Property 'checked' was updated. From false to true
Property 'default' was updated. From null to [complex value]
Property 'id' was updated. From 45 to null
Property 'key1' was removed
Property 'key2' was added with value: 'value2'
Property 'numbers2' was updated. From [complex value] to [complex value]
Property 'numbers3' was removed
Property 'numbers4' was added with value: [complex value]
Property 'obj1' was added with value: [complex value]
Property 'setting1' was updated. From 'Some value' to 'Another value'
Property 'setting2' was updated. From 200 to 300
Property 'setting3' was updated. From true to 'none'
```

## Демонстрация:
[![Actions Status](https://github.com/SergeiMed/java-project-lvl2/workflows/hexlet-check/badge.svg)](https://github.com/SergeiMed/java-project-lvl2/actions)
[![Actions Status](https://github.com/SergeiMed/java-project-lvl2/workflows/github-actions/badge.svg)](https://github.com/SergeiMed/java-project-lvl2/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/b46615d8109ff2d6d84c/maintainability)](https://codeclimate.com/github/SergeiMed/java-project-lvl2/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/b46615d8109ff2d6d84c/test_coverage)](https://codeclimate.com/github/SergeiMed/java-project-lvl2/test_coverage)
[![asciicast](https://asciinema.org/a/omSn73Okp0pH6aiaN8DPhvYnq.svg)](https://asciinema.org/a/omSn73Okp0pH6aiaN8DPhvYnq)
