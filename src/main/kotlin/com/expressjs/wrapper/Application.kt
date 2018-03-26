@file:Suppress("unused")

package com.expressjs.wrapper

open external class Application {

    /**
     * Express instance itself is a request handler, which could be invoked without
     * third argument.
     */
    val req: Request
    val res: Response

    /**
     * Initialize the server.
     *
     *   - setup default configuration
     *   - setup default middleware
     *   - setup route reflection methods
     */
    fun init()

    /**
     * Initialize application configuration.
     */
    fun defaultConfiguration()

    /**
     * Register the given template engine callback `fn`
     * as `ext`.
     *
     * By default will `require()` the engine based on the
     * file extension. For example if you try to render
     * a "foo.jade" file Express will invoke the following internally:
     *
     *     app.engine('jade', require('jade').__express);
     *
     * For engines that do not provide `.__express` out of the box,
     * or if you wish to "map" a different extension to the template engine
     * you may use this method. For example mapping the EJS template engine to
     * ".html" files:
     *
     *     app.engine('html', require('ejs').renderFile);
     *
     * In this case EJS provides a `.renderFile()` method with
     * the same signature that Express expects: `(path, options, callback)`,
     * though note that it aliases this method as `ejs.__express` internally
     * so if you're using ".ejs" extensions you dont need to do anything.
     *
     * Some template engines do not follow this convention, the
     * [Consolidate.js](https://github.com/visionmedia/consolidate.js)
     * library was created to map all of node's popular template
     * engines to follow this convention, thus allowing them to
     * work seamlessly within Express.
     */
    // TODO fun engine(ext: String, fn: Function): Application

    /**
     * Assign `setting` to `val`, or return `setting`'s value.
     *
     *    app.set('foo', 'bar');
     *    app.get('foo');
     *    // => "bar"
     *    app.set('foo', ['bar', 'baz']);
     *    app.get('foo');
     *    // => ["bar", "baz"]
     *
     * Mounted servers inherit their parent server's settings.
     */
    fun set(setting: String, `val`: Any): Application

    fun get(setting: String): String

    fun get(route: String, callback: (req: Request, res: Response) -> Unit)
    fun post(route: String, callback: (req: Request, res: Response) -> Unit)
    fun put(route: String, callback: (req: Request, res: Response) -> Unit)
    fun delete(route: String, callback: (req: Request, res: Response) -> Unit)

    fun param(name: String, handler: (req: Request, res: Response, next: NextResponse, value: Any, name: String) -> Any): Application
    fun param(name: Array<String>, handler: (req: Request, res: Response, next: NextResponse, value: Any, name: String) -> Any): Application

    /**
     * Return the app's absolute pathname
     * based on the parent(s) that have
     * mounted it.
     *
     * For example if the application was
     * mounted as "/admin", which itself
     * was mounted as "/blog" then the
     * return value would be "/blog/admin".
     */
    fun path(): String

    /**
     * Check if `setting` is enabled (truthy).
     *
     *    app.enabled('foo')
     *    // => false
     *
     *    app.enable('foo')
     *    app.enabled('foo')
     *    // => true
     */
    fun enabled(setting: String): Boolean

    /**
     * Check if `setting` is disabled.
     *
     *    app.disabled('foo')
     *    // => true
     *
     *    app.enable('foo')
     *    app.disabled('foo')
     *    // => false
     */
    fun disabled(setting: String): Boolean

    /** Enable `setting`. */
    fun enable(setting: String): Application

    /** Disable `setting`. */
    fun disable(setting: String): Application

    /**
     * Render the given view `name` name with `options`
     * and a callback accepting an error and the
     * rendered template string.
     *
     * Example:
     *
     *    app.render('email', { name: 'Tobi' }, function(err, html){
     *      // ...
     *    })
     */
    fun render(name: String, options: Any?, callback: ((err: Error, html: String) -> Unit)?)

    fun render(name: String, callback: (err: Error, html: String) -> Unit)

    /**
     * Listen for connections.
     *
     * A node `http.Server` is returned, with this
     * application (which is a `Function`) as its
     * callback. If you wish to create both an HTTP
     * and HTTPS server you may do so with the "http"
     * and "https" modules as shown here:
     *
     *    var http = require('http')
     *      , https = require('https')
     *      , express = require('express')
     *      , app = express();
     *
     *    http.createServer(app).listen(80);
     *    https.createServer({ ... }, app).listen(443);
     */
    fun listen(port: Int, hostname: String, backlog: Int, callback: Callback?) //TODO  http.Server;

    fun listen(port: Int, hostname: String, callback: Callback?) // TODO http.Server;
    fun listen(port: Int, callback: Callback?) // TODO http.Server;
    fun listen(path: String, callback: Callback?) // TODO http.Server;
    fun listen(handle: Any, callback: Callback?) // TODO http.Server;

    val router: String

    val settings: Any

    val resource: Any

    val map: Any

    val locals: Any

    /**
     * The app.routes object houses all of the routes defined mapped by the
     * associated HTTP verb. This object may be used for introspection
     * capabilities, for example Express uses this internally not only for
     * routing but to provide default OPTIONS behaviour unless app.options()
     * is used. Your application or framework may also remove routes by
     * simply by removing them from this object.
     */
    val routes: Any

    /**
     * Used to get all registered routes in Express Application
     */
    val _router: Any

    fun use(router: (req: Request, res: Response, next: NextResponse) -> Unit) // TODO : ApplicationRequestHandler<this>;
    fun use(router: Array<(req: Request, res: Response, next: NextResponse) -> Unit>) // TODO : ApplicationRequestHandler<this>;
    // fun use(path: String = definedExternally, router: Array<(req: Request, res: Response, next: (Error?) -> Unit) -> Unit>) // TODO : ApplicationRequestHandler<this>;
    // fun use(path: String = definedExternally, router: Array<(req: Request, res: Response, next: (Error?) -> Unit) -> Unit>) // TODO : ApplicationRequestHandler<this>;
}

typealias Callback = () -> Unit
typealias NextResponse = (Error?) -> Unit