package com.hx.core.wyim.service.impl;

import org.springframework.stereotype.Service;

import com.hx.core.wyim.entity.TeamUser;
import com.hx.core.wyim.service.TeamService;
import com.hx.core.wyim.team.TeamCommon;

/**
 * 群业务接口
 * 
 * @author Administrator
 *
 */
@Service
public class TeamServiceImpl implements TeamService {

	@Override
	public String create(TeamUser teamUser) throws Exception {
		return TeamCommon.create(teamUser.getTname(), teamUser.getOwner(), teamUser.getMembers(),
				teamUser.getAnnouncement(), teamUser.getIntro(), teamUser.getMsg(), teamUser.getMagree(),
				teamUser.getJoinmode(), teamUser.getCustom(), teamUser.getIcon(), teamUser.getBeinvitemode(),
				teamUser.getInvitemode(), teamUser.getUptinfomode(), teamUser.getUpcustommode());
	}

	@Override
	public String add(String tid, String owner, String members, int magree, String msg, String attach)
			throws Exception {

		return TeamCommon.add(tid, owner, members, magree, msg, attach);
	}

	@Override
	public String kick(String tid, String owner, String member, String attach) throws Exception {
		return TeamCommon.kick(tid, owner, member, attach);
	}

	@Override
	public String remove(String tid, String owner) throws Exception {
		return TeamCommon.remove(tid, owner);
	}

	@Override
	public String update(TeamUser teamUser) throws Exception {
		return TeamCommon.update(teamUser.getTid(), teamUser.getTname(), teamUser.getOwner(),
				teamUser.getAnnouncement(), teamUser.getIntro(), teamUser.getJoinmode(), teamUser.getCustom(),
				teamUser.getIcon(), teamUser.getBeinvitemode(), teamUser.getInvitemode(), teamUser.getUptinfomode(),
				teamUser.getUpcustommode());
	}

	@Override
	public String query(String tids, String ope) throws Exception {
		return TeamCommon.query(tids, ope);
	}

	@Override
	public String queryDetail(String tid) throws Exception {
		return TeamCommon.queryDetail(tid);
	}

	@Override
	public String getMarkReadInfo(String tid, String msgid, String fromAccid, Boolean snapshot) throws Exception {
		return TeamCommon.getMarkReadInfo(tid, msgid, fromAccid, snapshot);
	}

	@Override
	public String changeOwner(String tid, String owner, String newowner, int leave) throws Exception {
		
		return TeamCommon.changeOwner(tid, owner, newowner, leave);
	}

	@Override
	public String addManager(String tid, String owner, String members) throws Exception {
		return TeamCommon.addManager(tid, owner, members);
	}

	@Override
	public String removeManager(String tid, String owner, String members) throws Exception {
		return TeamCommon.removeManager(tid, owner, members);
	}

	@Override
	public String joinTeams(String accid) throws Exception {
		return TeamCommon.joinTeams(accid);
	}

	@Override
	public String updateTeamNick(String tid, String owner, String accid, String nick, String custom) throws Exception {
		return TeamCommon.updateTeamNick(tid, owner, accid, nick, custom);
	}

	@Override
	public String muteTeam(String tid, String accid, int ope) throws Exception {
		return TeamCommon.muteTeam(tid, accid, ope);
	}

	@Override
	public String muteTlist(String tid, String owner, String accid, int mute) throws Exception {
		return TeamCommon.muteTlist(tid, owner, accid, mute);
	}

	@Override
	public String leave(String tid, String accid) throws Exception {
		return TeamCommon.leave(tid, accid);
	}

	@Override
	public String muteTlistAll(String tid, String owner, String mute, int muteType) throws Exception {
		return TeamCommon.muteTlistAll(tid, owner, mute, muteType);
	}

	@Override
	public String listTeamMute(String tid, String owner) throws Exception {
		return TeamCommon.listTeamMute(tid, owner);
	}

}
