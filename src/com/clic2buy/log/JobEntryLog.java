package com.clic2buy.log;

import java.util.List;

import org.pentaho.di.cluster.SlaveServer;
import org.pentaho.di.core.CheckResultInterface;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.Result;
import org.pentaho.di.core.annotations.JobEntry;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleDatabaseException;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleJobException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entry.JobEntryBase;
import org.pentaho.di.job.entry.JobEntryInterface;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.Repository;
import org.pentaho.metastore.api.IMetaStore;
import org.w3c.dom.Node;

@JobEntry(
    id = "JobEntryLog",
    name = "LogJobEntry.Name",
    description = "LogJobEntry.TooltipDesc",
    image = "com/clic2buy/log/resources/DPL.svg",
    categoryDescription = "i18n:org.pentaho.di.job:JobCategory.Category.Conditions",
    i18nPackageName = "org.pentaho.di.sdk.samples.jobentries.log",
    documentationUrl = "LogJobEntry.DocumentationURL",
    casesUrl = "LogJobEntry.CasesURL",
    forumUrl = "LogJobEntry.ForumURL"
  )
public class JobEntryLog extends JobEntryBase implements Cloneable, JobEntryInterface {
  public JobEntryLog() {
    this( "" );
  }

  public JobEntryLog( String name ) {
    super( name, "" );
  }

  public Object clone() {
    JobEntryLog je = (JobEntryLog) super.clone();
    return je;
  }

  public String getXML() {
    StringBuilder retval = new StringBuilder( 200 );

    retval.append( super.getXML() );
    return retval.toString();
  }

  public void loadXML( Node entrynode, List<DatabaseMeta> databases, List<SlaveServer> slaveServers,
    Repository rep, IMetaStore metaStore ) throws KettleXMLException {
    try {
      super.loadXML( entrynode, databases, slaveServers );
    } catch ( KettleException e ) {
      throw new KettleXMLException( "Unable to load job entry of type 'log' from XML node", e );
    }
  }

  public void loadRep( Repository rep, IMetaStore metaStore, ObjectId id_jobentry, List<DatabaseMeta> databases,
    List<SlaveServer> slaveServers ) throws KettleException {
  }

  // Save the attributes of this job entry
  //
  public void saveRep( Repository rep, IMetaStore metaStore, ObjectId id_job ) throws KettleException {
  }

  public Result execute( Result result, int nr ) throws KettleJobException {
    parentJob.setVariable("LOG_TEXT", result.getLogText().replace("'", "''"));
    result.setResult(true);
    result.setNrErrors( 0 );
    return result;
  }

  @Override
  public void check( List<CheckResultInterface> remarks, JobMeta jobMeta, VariableSpace space,
    Repository repository, IMetaStore metaStore ) {

  }

}
