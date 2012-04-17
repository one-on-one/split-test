<div class="dialog">
    <table>
        <tbody>

        <tr class="prop">
            <td valign="top" class="name">
                <label for="name"><g:message code="splitTest.name.label" default="Name" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: splitTestInstance, field: 'name', 'errors')}">
                <g:textField name="name" value="${splitTestInstance?.name}" size="50" />
            </td>
        </tr>

        <tr class="prop">
            <td valign="top" class="name">
                <label for="status"><g:message code="splitTest.status.label" default="Status" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: splitTestInstance, field: 'status', 'errors')}">
                <select name="status" id="status">
                    <option value="${splitTestInstance?.PENDING}"
                            <g:if test="${splitTestInstance?.status == splitTestInstance?.PENDING}">selected="selected"</g:if>
                    >Pending</option>
                    <option value="${splitTestInstance?.RUNNING}"
                            <g:if test="${splitTestInstance?.status == splitTestInstance?.RUNNING}">selected="selected"</g:if>
                    >Running</option>
                    <option value="${splitTestInstance?.PAUSED}"
                            <g:if test="${splitTestInstance?.status == splitTestInstance?.PAUSED}">selected="selected"</g:if>
                    >Paused</option>
                    <option value="${splitTestInstance?.COMPLETED}"
                            <g:if test="${splitTestInstance?.status == splitTestInstance?.COMPLETED}">selected="selected"</g:if>
                    >Completed</option>
                </select>
            </td>
        </tr>

        <tr class="prop">
            <td valign="top" class="name">
                <label for="dateStart"><g:message code="splitTest.dateStart.label" default="Date Start" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: splitTestInstance, field: 'dateStart', 'errors')}">
                <g:datePicker name="dateStart" precision="hour" value="${splitTestInstance?.dateStart}"  />
            </td>
        </tr>

        <tr class="prop">
            <td valign="top" class="name">
                <label for="dateFinish"><g:message code="splitTest.dateFinish.label" default="Date Finish" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: splitTestInstance, field: 'dateFinish', 'errors')}">
                <g:datePicker name="dateFinish" precision="hour" value="${splitTestInstance?.dateFinish}"  />
            </td>
        </tr>

        </tbody>
    </table>
</div>