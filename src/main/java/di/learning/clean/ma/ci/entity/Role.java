package di.learning.clean.ma.ci.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String roleName;

    private Date createDat;

    private Date updateDat;

    private String status;

    @OneToMany(mappedBy = "role")
    private List<User> userList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;
        return Objects.equals(getRoleId(), role.getRoleId()) && Objects.equals(getRoleName(), role.getRoleName()) && Objects.equals(getCreateDat(), role.getCreateDat()) && Objects.equals(getUpdateDat(), role.getUpdateDat()) && Objects.equals(getStatus(), role.getStatus()) && Objects.equals(getUserList(), role.getUserList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleId(), getRoleName(), getCreateDat(), getUpdateDat(), getStatus(), getUserList());
    }
}
