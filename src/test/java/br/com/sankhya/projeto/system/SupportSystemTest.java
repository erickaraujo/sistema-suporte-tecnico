package br.com.sankhya.projeto.system;

import br.com.sankhya.projeto.model.*;
import com.oracle.svm.core.annotate.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SupportSystemTest {

    @Test
    void issueResolvedAtLevel1() throws Exception{
        CallCenterLevel callCenterLevel = Mockito.spy(new CallCenterLevel());
        SystemAnalystLevel systemAnalystLevel = Mockito.spy(new SystemAnalystLevel());

        Mockito.doReturn(true).when(callCenterLevel).resolveIssue(Mockito.any(SupportRequestInfo.class));

        SupportSystem sys = new SupportSystem(SupportLevel.chain(callCenterLevel, systemAnalystLevel));

        sys.processSupportRequest("\n" +
                "\n" +
                "Lorem ipsum dolor sit amet. ");

        verify(callCenterLevel, Mockito.times(1)).resolveIssue(Mockito.any(SupportRequestInfo.class));
        verify(systemAnalystLevel, Mockito.times(0)).resolveIssue(Mockito.any(SupportRequestInfo.class));
    }

    @Test
    void issueResolvedAtLevel2() throws Exception{
        CallCenterLevel callCenterLevel = Mockito.spy(new CallCenterLevel());
        SystemAnalystLevel systemAnalystLevel = Mockito.spy(new SystemAnalystLevel());

        Mockito.doReturn(true).when(systemAnalystLevel).resolveIssue(Mockito.any(SupportRequestInfo.class));

        SupportSystem sys = new SupportSystem(SupportLevel.chain(callCenterLevel, systemAnalystLevel));

        sys.processSupportRequest("\n" +
                "\n" +
                "Lorem ipsum dolor sit amet. ");

        verify(callCenterLevel, Mockito.times(1)).resolveIssue(Mockito.any(SupportRequestInfo.class));
        verify(systemAnalystLevel, Mockito.times(1)).resolveIssue(Mockito.any(SupportRequestInfo.class));
    }
}
