package top.wenxyn.partner.manager.common;
/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
public interface Constant {
    int DEFAULT_PAGE_SIZE = 10;

    String VERIFY_CODE_SESSION_NAME = "verifyCode";
    String USER_DETAILS_SUFFIX = "user_details";
    String SEPARATE_COMMA = "_";

    String ACTIVE_USER_COUNT = "activeUserCount";

    enum YNState{
        Y(1),
        N(0);
        Integer state;
        YNState(Integer state){
            this.state = state;
        }

        public Integer getState() {
            return state;
        }
    }

    enum PartnerState{
        POTENTIAL(0),
        APPLYING(1),
        CHECKED(2),
        BANNED(-1);

        Integer state;
        PartnerState(Integer state){
            this.state = state;
        }

        public Integer getState() {
            return state;
        }
    }

    enum CheckCode{
        PARTNER(0),
        CONTRACT(1);

        Integer state;
        CheckCode(Integer state){
            this.state = state;
        }

        public Integer getState() {
            return state;
        }
    }

    enum ContractState{
        APPLYING(0),
        CHECKED(1),
        EXECUTING(2),
        FINISHED(3),
        ILLEGAL(-1);

        Integer state;
        ContractState(Integer state){
            this.state = state;
        }

        public Integer getState() {
            return state;
        }
    }
}
