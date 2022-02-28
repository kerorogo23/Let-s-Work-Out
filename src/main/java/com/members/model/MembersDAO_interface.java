package com.members.model;

import java.util.*;

public interface MembersDAO_interface {
    public void insert(MembersVO membersVO);
    public void update(MembersVO membersVO);
    public void delete(Integer membersVO);
    public MembersVO findByPrimaryKey(Integer membersVO);
    public MembersVO findByPrimaryKeyByMemAcc(String memAcc);
    public List<MembersVO> getAll();

}
