
#【mybatis入门第二步——使用接口interface】

与原始的区别总结：
原始的是获取sqlSession，然后调用session的selectOne方法，selectOne方法内部是调用mapper实例，在mapper实例中查询结果user对象；

使用接口的时候呢: session会依据user-mapper.xml生成实现指定接口的mapper实例，然后调用这个mapper实例的方法，查询到user对象；
比原始方式进步的地方是不再通过调用session自己的selectOne等方法查询,变为了获取派生自接口的mapper实例, 然后直接调用mapper实例(具体见本demo代码)