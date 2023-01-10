## readpassword

This is a cross platform `readpassword` utility useful for CLIs that require reading password from stdin.
It acts like standard unix style of password input, like in `sudo` where password is hidden on input.

### Usage

This library is currently available for Scala binary versions 2.13 and 3.2 on JVM and Scala native.

To use the latest version, include the following in your `build.sbt`:

```scala
libraryDependencies ++= Seq(
  "dev.hnaderi" %%% "readpassword" % "<see badge>"
)
```

Then use it like:

``` scala
import dev.hnaderi.readpassword

object Main extends App {
  val pass = readpassword.read("Enter your password: ")
  println(s"Your password is: $pass")
}
```

And then:

``` plain
sbt:root> exampleNative/run
Enter your password: 
Your password is: This is my password!
```

### Supports
| env                  | native | jvm |
|:--------------------:|:------:|:---:|
| linux                | ✅     | ✅  |
| macos                | ✅     | ✅  |
| posix compliant OSes | ✅     | ✅  |
| windows              | ✅     | ✅  |
