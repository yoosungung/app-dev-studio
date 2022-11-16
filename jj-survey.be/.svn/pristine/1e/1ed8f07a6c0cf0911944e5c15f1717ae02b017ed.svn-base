package kr.ac.jj.survey.infrastructure.framework.core.support.collection;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 2059672305553981127L;

    private DataJobTypes _JOB_TYPE;
    private LinkedHashMap<String, Object> _META_MAP;

    @JsonIgnore
    public DataJobTypes get_JOB_TYPES() {
        return this._JOB_TYPE;
    }

    @JsonIgnore
    public void set_JOB_TYPES(DataJobTypes _JOB_TYPE) {
        this._JOB_TYPE = _JOB_TYPE;
    }

    public String get_JOB_TYPE() {
        if (this._JOB_TYPE == null) {
            return null;
        }

        return this._JOB_TYPE.getShortValue();
    }

    public void set_JOB_TYPE(String _JOB_TYPE) {
        if (StringUtils.isEmpty(_JOB_TYPE)) {
            this._JOB_TYPE = null;
        } else if (_JOB_TYPE.length() == 1) {
            this._JOB_TYPE = DataJobTypes.shortValueOf(_JOB_TYPE);
        } else {
            this._JOB_TYPE = DataJobTypes.valueOf(_JOB_TYPE);
        }
    }

    public LinkedHashMap<String, Object> get_META_MAP() {
        return this._META_MAP;
    }

    public void set_META_MAP(LinkedHashMap<String, Object> _META_MAP) {
        this._META_MAP = _META_MAP;
    }

    public Object get_META(String key) {
        if (this._META_MAP == null) {
            return null;
        }

        return this._META_MAP.get(key);
    }

    public void set_META(String key, Object value) {
        if (value == null) {
            if (this._META_MAP != null && this._META_MAP.containsKey(key)) {
                this._META_MAP.remove(key);
            }
        } else {
            if (this._META_MAP == null) {
                this._META_MAP = new LinkedHashMap<String, Object>();
            }

            this._META_MAP.put(key, value);
        }
    }

    public void clear_META() {
        if (this._META_MAP != null) {
            this._META_MAP.clear();
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
