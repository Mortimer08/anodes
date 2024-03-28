package common.model;

import org.apache.commons.codec.language.DaitchMokotoffSoundex;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
public abstract class TimeStamped extends BaseModel {
    public Boolean active = false;
    public Date created = new Date();
    public Date updated = new Date();

    @PrePersist
    public void prePersist() {
        if (created == null)
            created = new Date();
        updated = created;
    }

    @PreUpdate
    public void preUpdate() {
        updated = new Date();
        if (created == null)
            created = updated;
    }

}
