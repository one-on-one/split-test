<fieldset>
    <div class="control-group">
        <label class="control-label">Split Test</label>
        <div class="controls">
            <g:hiddenField name="splitTest.id" value="${splitTestVariantInstance?.splitTest?.id}"/>
            <span class="input-xlarge uneditable-input">${splitTestVariantInstance?.splitTest?.name}</span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="name">Name</label>
        <div class="controls">
            <g:textField class="input-xlarge" name="name" value="${splitTestVariantInstance?.name}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="name">Is Active?</label>
        <div class="controls">
            <label class="checkbox inline" for="isActive">
                <g:checkBox name="isActive" value="${splitTestVariantInstance?.isActive}"/>
                If checked, the split test variant will be included in the test.
            </label>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="name">Code</label>
        <div class="controls">
            <g:textArea name="code" id="code" cols="80" rows="20" style="width: 80%; height: 350px;"
                        value="${splitTestVariantInstance?.code}"/>
        </div>
    </div>
</fieldset>

