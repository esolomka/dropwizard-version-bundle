## Getting Started

Just add this maven dependency to get started:

In your pom.xml define `<dropwizard.version.bundle>0.x</dropwizard-version-bundle.version>`

Make sure to keep `dropwizard-version-bundle.version in sync` with the major dropwizard version (0.6, 0.7, 0.8)

Dropwizad majorVersion (0.6, 0.7, 0.8) example :

```xml

<dropwizard-version-bundle.version>0.6</dropwizard-version-bundle.version>

...

<dependency>
  <groupId>fr.novapost.dropwizard-bundles</groupId>
  <artifactId>dropwizard-version-bundle_2.11</artifactId>
  <version>${dropwizard-version-bundle.version}</version>
</dependency>
```


Add the bundle to your environment using your choice of version supplier:

```java
public class MyApplication extends Application<Configuration> {
  @Override
  public void initialize(Bootstrap<OpentrustConfiguration> bootstrap) {
    ...
    bootstrap.addBundle(new VersionBundle(new PropsVersionSupplier()));
    ...
  }

  @Override
  public void run(Configuration cfg, Environment env) throws Exception {
    // ...
  }
}
```

Now you can access the the `/version` URL of your application to see the version


For example if your application were running on `localhost` then
something like the following would show you your application's version.

```bash
curl localhost:8080/version

{"version": "X.XX", "name": "appName"}

```