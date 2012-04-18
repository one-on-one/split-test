# Split Test Plugin for Grails

Performing [A/B tests](http://en.wikipedia.org/wiki/A/B_testing) in your grails application allows you to make informed decisions on design and user experience 
based on visitors actions. The information is gathered by placing a simple tag on your view, then a trigger on your controller
to inform the plugin that the user was successfully converted.

Some of the key features of this plugin are:

* Test display or behavioral differences with one simple taglib `<ab:splitTest>`
* Measure not only conversions, but steps along the way (up to 3 - see below)
* Analytics on visitor behavior and which paths they are likely to convert better on
* Very fast, with minimal performance impact
* Beautiful admin tool (/splitTests) based on Twitter Bootstrap

*Screenshots to follow*

## Quick Start

A split test is comprised of placing a taglib in a view. The taglib will contain the default contents of which are to be shown if the test is not enabled. For example:

```html
<ab:splitTest name="My First Split Test">
    This is my default content that appears when the test is disabled.
</ab:splitTest>
```

When this page is viewed by a visitor, a Split Test is automatically added into the Split Test list. Note that it is not active by default.

To trigger the conversion, you can do this via the splitTestService in your controller:

```groovy
class ExampleController {

    def splitTestService

    def converted() {
        // Mark split test with name 'My First Split Test' as converted
        splitTestService.markTestAsConverted('My First Split Test')
    }
}
```
    
You can also trigger different conversion metrics using the Action 1-3 triggers:

```groovy
class ExampleController {

    def splitTestService

    def almostConverted() {
        // Mark split test as converting on Action 1
        splitTestService.markTestAsActionOneConverted('My First Split Test')

        // Mark split test as converting on Action 2
        splitTestService.markTestAsActionTwoConverted('My First Split Test')

        // Mark split test as converting on Action 3
        splitTestService.markTestAsActionThreeConverted('My First Split Test')
    }

    def converted() {
        // Mark split test with name 'My First Split Test' as converted
        splitTestService.markTestAsConverted('My First Split Test')
    }
}
```