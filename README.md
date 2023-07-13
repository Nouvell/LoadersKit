# Loaders Kit 2

[![Maven Central](https://img.shields.io/maven-central/v/io.github.nouvell/loaderskit.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.nouvell/loaderskit)  
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

Loader Kit 2 is a jetpack compose loader animations library based on [Andrei Iancu's](https://www.figma.com/@iancu)  [Loader Kit 2 / Three Steps](https://www.figma.com/community/file/1113086966127751393/Loaders-Kit-2-%2F-Three-Steps)  
design specification.

# Using Loaders Kit 2

### Download

```kotlin
repositories {
  mavenCentral()
}

dependencies {
  implementation 'io.github.nouvell:loaderskit:1.0.0'
}
```

### Usage

Import the loader you want use like in the example below:
The name of the loader to use can be gleaned from the [Reference Table](https://github.com/Nouvell/LoadersKit/blob/main/README.md#loader-reference).

```kotlin
// import the version of the loader you want to use
Loader02(  
    modifier = Modifier.size(150.dp),
    color = LoaderColor.Black
)
```
#### Custom Colors

Loaders Kit 2 provides three colors out of the box; `LoaderColor.Black ` ,`LoaderColor.White`, `LoaderColor.Rainbow`. To use a custom color you can provide a `Single` or `Multi` color object.

```kotlin
// Single
Loader04(
    color = LoaderColor.Single(Color(0xFFFF00FF))
)


// Multi
Loader02( 
    color = LoaderColor.Multi(  
        listOf(  
            Color(0xFFEB5757),  
            Color(0xFFF2994A),  
            Color(0xFFF2C94C),  
            Color(0xFF9B51E0),  
        )  
    )  
)
```

### Loader Reference

| Loader Name | Preview                                                                                                        |
|-------------|----------------------------------------------------------------------------------------------------------------|
| Loader01    | <img src="https://raw.githubusercontent.com/Nouvell/LoadersKit/main/resources/loader-01.gif" width="100" height="100" /> |
| Loader02    | <img src="https://raw.githubusercontent.com/Nouvell/LoadersKit/main/resources/loader-02.gif" width="100" height="100" /> |
| Loader03    | Coming Soon                                                                                                    |
| Loader04    | <img src="https://raw.githubusercontent.com/Nouvell/LoadersKit/main/resources/loader-04.gif" width="100" height="100" /> |
| Loader05    | Coming Soon                                                                                                    |
| Loader06    | <img src="https://raw.githubusercontent.com/Nouvell/LoadersKit/main/resources/loader-06.gif" width="100" height="100" /> |
| Loader07    | <img src="https://raw.githubusercontent.com/Nouvell/LoadersKit/main/resources/loader-07.gif" width="100" height="100" /> |
| Loader08    | <img src="https://raw.githubusercontent.com/Nouvell/LoadersKit/main/resources/loader-08.gif" width="100" height="100" /> |
| Loader09    | <img src="https://raw.githubusercontent.com/Nouvell/LoadersKit/main/resources/loader-09.gif" width="100" height="100" /> |
| Loader10    | <img src="https://raw.githubusercontent.com/Nouvell/LoadersKit/main/resources/loader-10.gif" width="100" height="100" /> |
| Loader11    | <img src="https://raw.githubusercontent.com/Nouvell/LoadersKit/main/resources/loader-11.gif" width="100" height="100" /> |
| Loader12    | <img src="https://raw.githubusercontent.com/Nouvell/LoadersKit/main/resources/loader-12.gif" width="100" height="100" /> |
| Loader13    | Coming Soon                                                                                                    |
| Loader14    | <img src="https://raw.githubusercontent.com/Nouvell/LoadersKit/main/resources/loader-14.gif" width="100" height="100" /> |
| Loader15    | <img src="https://raw.githubusercontent.com/Nouvell/LoadersKit/main/resources/loader-15.gif" width="100" height="100" /> |
| Loader16    | Coming Soon                                                                                                    |
| Loader17    | <img src="https://raw.githubusercontent.com/Nouvell/LoadersKit/main/resources/loader-17.gif" width="100" height="100" /> |
| Loader18    | Coming Soon                                                                                                    |
