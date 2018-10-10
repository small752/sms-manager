package com.tardis.sms.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sms_city")
public class City implements Serializable {
	
	private static final long serialVersionUID = 4024604171780585241L;

	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", unique = true, nullable = false, length = 32)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String state;
    
    @Version
    @Column(name = "VERSION")
    private int version;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time")
	private Date modifyTime;
	

    // ... additional members, often include @OneToMany mappings

    protected City() {
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }
    
    public String getId() {
		return id;
	}
    
    public void setId(String id) {
		this.id = id;
	}

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }
    
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}

}
