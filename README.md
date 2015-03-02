Preference
===========
![](logo.png)

*Yeah, well, I'm gonna go build my own Android reality, with preference and poetesses*


Preference is a little library that allows you to work with shared preferences via user-friendly DSL. It lets you forget about awful boilerplate code that you used to write for working with shared preferences in Android. It is written in [Kotlin](http://kotlinlang.org).

## Including in your project
```groovy
repositories {
    mavenCentral()
}

dependencies {
    compile 'ru.freedomlogic:preference:0.2@aar'
}
```

Import `ru.freedomlogic.preference.*` to use Preference DSL in your classes.

## Usage
Editing shared preferences
```kotlin
	preferencesEditor(
		"server"  to "http://github.com"
		"port"    to 80
	)
```

Reading shared preferences
```kotlin
	val remoteHost = RemoteHost()

	preferences {
		with(remoteHost) {
			server = getString("server")
			port   = getInt("port")
		}
	}
```


## Contribute
I accept every meaningful pull request that you might offer. All issues will be reviewed and taken into account.

