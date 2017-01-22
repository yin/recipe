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
the boiler-code required. It is a client-server application, which invokes
remote RPC service and fetches data (in json format).

TODO: Example of remote vs. local service call using Guice injection

The Master Plan
===============
With recipe you can define certain architectural keystones of you application
and have it generated into multiple languages. The output of a recipe for a
specified target language is called ****target materialization****.

Given, that the APIs and behaviour of each target materialization are verified,
we can achieve free language-interoperability.

*If you have to work with coder preferring different day-time langauges, you
should read further.*

The Master Plan Details
=======================
The idea behind the free interoperability statement goes as follows:

> If you express your application in nouns and verbs, you can use these to
> write all application APIs, data structures and service implementations in
> any programming language.

You only need to describe the terms. These map 1:1 to recipe core ingredients:

1. Message
2. Service
3. Collection (basically Service with predefined verbs)

If you describe a message in the stand-in JSON recipe format:

````json
{
  "name": "simple-recipe",
  "targets": ["java"],
  "ingredients": [{
    "message": {
      "name": "Message01",
      "fields": [{
        "name": "id",
        "type": "int64",
        "modifiers": []
      }, {
        "name": "text",
        "type": "string",
        "modifiers": ["OPTIONAL"]
      }]
    }
  }]
}
````

You can publish outputs for target languages of all your teams and have them
use the same API across all languages (you may want different naming
conventions for your target materializations, but that could be solved).

It would be really nice in future, if Recipe appeals to coders of any
language. Don't let the Java put you down! There is no planned dependency
on Java platform, nor any other platform.
