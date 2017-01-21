getter(field) ::= <<
    @JsonProperty("<field.name>")
    public abstract <filed.javaType> <field.name>();
>>

setter(field) ::= <<
        @JsonProperty("<field.name>")
        public abstract Builder <field.name>(<field.javaType> <field.name>);
>>

parameters(fields) ::= "<fields:parameter();separator=", ">"
parameter(field) ::= "<field.javaType> <field.name>"
setFields(fields) ::= "<message.fields:setField()>"
setField(field(field) ::= ".<field.name>(<field.name>)"
createMethod(message) ::= <<
    public static <message.name> create(<parameters(message.fields)>) {
        return builder()<setFields(message.fields)>.build();
    }
>>

messageGetters(fields) ::= "<message.fields:getter()>"
messageSetters(fields) ::= "<message.fields:setField()>"

output(java, message) ::= <<
package <java.package>;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = AutoValue_<message.name>.Builder.class)
public abstract class <message.name> {

    <messageGetters(message.fields)>

    public static Builder builder() {
        return new AutoValue_<message.name>.Builder();
    }

    <createMethod(message)>

    @AutoValue.Builder
    public static abstract class Builder {
        <messageSetters(message.fields)>

        public abstract Item build();
    }
}
>>
