Recipe
======

Recipe is a program which writes programs. Now just a simple code-generator
really.

A Recipe is a file describing data in a structure. It consist of pieces
of information called ingredients. You can "````cook````" a Recipe to get what
you described in it. You can use the outputs in your code or build process or
as resources at runtime.

java-recipe module
==================
Bootstrap Java implementation of Recipe.

java-example module
===================
An example where Recipes are going.

A collection of different snippets. The goal here is to be able to generate all
the bioler-code required. It is a client-server application, which invokes
remote RPC service and fetches data (in json format).

TODO: Example of remote vs. local service call using Guice injection
