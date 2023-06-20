# Default Server Properties

A very simple mod that mixins into the game to allow a modpack developer to define a `default-server.properties` in the root of the pack. This will then override the default creation of the `server.properties` file. The beauty of this method is that you only need to set the properties that you want to change by default in the `default-server.properties` as the games native loading will take care of the rest.

## How to use

Create a `default-server.properties` file in the games root folder. If there is no `default-used.marker` in the `local` folder then the mod will attempt to load that default data in and inject it to the normal default creation of the `server.properties`... simples.

## Versioning

Why is your version number set so high?

That's a good question! the version number uses the incremental count of Minecraft releases as a major version as each Minecraft release defines a new major release for the mod.

Because of this, it means that our minor version is effectively our major and our patch is a combined patch and minor if you're used to how semver works. It's not amazing but when you're chasing a never ending target, this is about the only good way I've found to ensure there is never a version collision between different versions of the mod that are being actively maintained...

If you have any recommendations, feel free to let me know!...
