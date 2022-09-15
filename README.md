# Default Server Properties

A very simple mod that mixins into the game to allow a modpack developer to define a `default-server.properties` in the root of the pack. This will then override the default creation of the `server.properties` file. The beauty of this method is that you only need to set the properties that you want to change by default in the `default-server.properties` as the games native loading will take care of the rest.

## How to use

Create a `default-server.properties` file in the games root folder. If there is no `default-used.marker` in the `local` folder then the mod will attempt to load that default data in and inject it to the normal default creation of the `server.properties`... simples.
