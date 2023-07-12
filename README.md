# Loaders Kit 2

[![Maven Central](https://img.shields.io/maven-central/v/com.squareup.workflow1/workflow-core-jvm.svg?label=Maven%20Central)](https://central.sonatype.com/namespace/com.squareup.workflow1)  
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

| Loader Name | Preview |
|-------------|---------|
| Loader01    | ![Loader 01](https://github.com/Nouvell/LoadersKit/blob/main/resources/loader-01.gif)   |
|             |         |
|             |         |
|             |         |
|             |         |
|             |         |
