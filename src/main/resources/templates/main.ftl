<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>
        <@l.logout />
        <span><a href="/user">User list</a></span>
    </div>
    <div>
        <form method="post">
            <input type="text" name="tag" placeholder="Введите тэг">
            <input type="text" name="text" placeholder="Введите текст">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить</button>
        </form>
    </div>

    <div>Список сообщений</div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter}">
        <button type="submit">Найти</button>
    </form>
    <!--    Непустой список-->
    <#list messages as message>
    <div>
        <b>${message.id}</b>
        <span>${message.tag}</span>
        <i>${message.text}</i>
        <strong>${message.authorName}</strong>
    </div>
        <#else>
        No messages
    </#list>
</@c.page>