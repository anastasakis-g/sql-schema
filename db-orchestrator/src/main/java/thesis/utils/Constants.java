package thesis.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {
    private static final String COLLATION_CHARACTER_SET_APPLICABILITY = "COLLATION_CHARACTER_SET_APPLICABILITY";
    private static final String CHARACTER_SETS = "CHARACTER_SETS";
    private static final String COLLATIONS = "COLLATIONS";
    private static final String COLUMN_PRIVILEGES = "COLUMN_PRIVILEGES";
    private static final String COLUMN_STATISTICS = "COLUMN_STATISTICS";
    private static final String COLUMNS = "COLUMNS";
    private static final String ENGINES = "ENGINES";
    private static final String EVENTS = "EVENTS";
    private static final String FILES = "FILES";
    private static final String INNODB_BUFFER_PAGE = "INNODB_BUFFER_PAGE";
    private static final String INNODB_BUFFER_PAGE_LRU = "INNODB_BUFFER_PAGE_LRU";
    private static final String INNODB_BUFFER_POOL_STATS = "INNODB_BUFFER_POOL_STATS";
    private static final String INNODB_CACHED_INDEXES = "INNODB_CACHED_INDEXES";
    private static final String INNODB_CMP = "INNODB_CMP";
    private static final String INNODB_CMP_PER_INDEX = "INNODB_CMP_PER_INDEX";
    private static final String INNODB_CMP_PER_INDEX_RESET = "INNODB_CMP_PER_INDEX_RESET";
    private static final String INNODB_CMP_RESET = "INNODB_CMP_RESET";
    private static final String INNODB_CMPMEM = "INNODB_CMPMEM";
    private static final String INNODB_CMPMEM_RESET = "INNODB_CMPMEM_RESET";
    private static final String INNODB_COLUMNS = "INNODB_COLUMNS";
    private static final String INNODB_DATAFILES = "INNODB_DATAFILES";
    private static final String INNODB_FIELDS = "INNODB_FIELDS";
    private static final String INNODB_FOREIGN = "INNODB_FOREIGN";
    private static final String INNODB_FOREIGN_COLS = "INNODB_FOREIGN_COLS";
    private static final String INNODB_FT_BEING_DELETED = "INNODB_FT_BEING_DELETED";
    private static final String INNODB_FT_CONFIG = "INNODB_FT_CONFIG";
    private static final String INNODB_FT_DEFAULT_STOPWORD = "INNODB_FT_DEFAULT_STOPWORD";
    private static final String INNODB_FT_DELETED = "INNODB_FT_DELETED";
    private static final String INNODB_FT_INDEX_CACHE = "INNODB_FT_INDEX_CACHE";
    private static final String INNODB_FT_INDEX_TABLE = "INNODB_FT_INDEX_TABLE";
    private static final String INNODB_INDEXES = "INNODB_INDEXES";
    private static final String INNODB_METRICS = "INNODB_METRICS";
    private static final String INNODB_SESSION_TEMP_TABLESPACES = "INNODB_SESSION_TEMP_TABLESPACES";
    private static final String INNODB_TABLES = "INNODB_TABLES";
    private static final String INNODB_TABLESPACES = "INNODB_TABLESPACES";
    private static final String INNODB_TABLESPACES_BRIEF = "INNODB_TABLESPACES_BRIEF";
    private static final String INNODB_TABLESTATS = "INNODB_TABLESTATS";
    private static final String INNODB_TEMP_TABLE_INFO = "INNODB_TEMP_TABLE_INFO";
    private static final String INNODB_TRX = "INNODB_TRX";
    private static final String INNODB_VIRTUAL = "INNODB_VIRTUAL";
    private static final String KEY_COLUMN_USAGE = "KEY_COLUMN_USAGE";
    private static final String KEYWORDS = "KEYWORDS";
    private static final String OPTIMIZER_TRACE = "OPTIMIZER_TRACE";
    private static final String PARAMETERS = "PARAMETERS";
    private static final String PARTITIONS = "PARTITIONS";
    private static final String PLUGINS = "PLUGINS";
    private static final String PROCESSLIST = "PROCESSLIST";
    private static final String PROFILING = "PROFILING";
    private static final String REFERENTIAL_CONSTRAINTS = "REFERENTIAL_CONSTRAINTS";
    private static final String RESOURCE_GROUPS = "RESOURCE_GROUPS";
    private static final String ROUTINES = "ROUTINES";
    private static final String SCHEMA_PRIVILEGES = "SCHEMA_PRIVILEGES";
    private static final String SCHEMATA = "SCHEMATA";
    private static final String ST_GEOMETRY_COLUMNS = "ST_GEOMETRY_COLUMNS";
    private static final String ST_SPATIAL_REFERENCE_SYSTEMS = "ST_SPATIAL_REFERENCE_SYSTEMS";
    private static final String STATISTICS = "STATISTICS";
    private static final String TABLE_CONSTRAINTS = "TABLE_CONSTRAINTS";
    private static final String TABLE_PRIVILEGES = "TABLE_PRIVILEGES";
    private static final String TABLES = "TABLES";
    private static final String TABLESPACES = "TABLESPACES";
    private static final String TRIGGERS = "TRIGGERS";
    private static final String USER_PRIVILEGES = "USER_PRIVILEGES";
    private static final String VIEW_ROUTINE_USAGE = "VIEW_ROUTINE_USAGE";
    private static final String VIEW_TABLE_USAGE = "VIEW_TABLE_USAGE";
    private static final String VIEWS = "VIEWS";

    public static List<String> ignoredTables() {
        return Arrays.asList(
                CHARACTER_SETS,
                COLLATION_CHARACTER_SET_APPLICABILITY,
                COLLATIONS,
                COLUMN_PRIVILEGES,
                COLUMN_STATISTICS,
                COLUMNS,
                ENGINES,
                EVENTS,
                FILES,
                INNODB_BUFFER_PAGE,
                INNODB_BUFFER_PAGE_LRU,
                INNODB_BUFFER_POOL_STATS,
                INNODB_CACHED_INDEXES,
                INNODB_CMP,
                INNODB_CMP_PER_INDEX,
                INNODB_CMP_PER_INDEX_RESET,
                INNODB_CMP_RESET,
                INNODB_CMPMEM,
                INNODB_CMPMEM_RESET,
                INNODB_COLUMNS,
                INNODB_DATAFILES,
                INNODB_FIELDS,
                INNODB_FOREIGN,
                INNODB_FOREIGN_COLS,
                INNODB_FT_BEING_DELETED,
                INNODB_FT_CONFIG,
                INNODB_FT_DEFAULT_STOPWORD,
                INNODB_FT_DELETED,
                INNODB_FT_INDEX_CACHE,
                INNODB_FT_INDEX_TABLE,
                INNODB_INDEXES,
                INNODB_METRICS,
                INNODB_SESSION_TEMP_TABLESPACES,
                INNODB_TABLES,
                INNODB_TABLESPACES,
                INNODB_TABLESPACES_BRIEF,
                INNODB_TABLESTATS,
                INNODB_TEMP_TABLE_INFO,
                INNODB_TRX,
                INNODB_VIRTUAL,
                KEY_COLUMN_USAGE,
                KEYWORDS,
                OPTIMIZER_TRACE,
                PARAMETERS,
                PARTITIONS,
                PLUGINS,
                PROCESSLIST,
                PROFILING,
                REFERENTIAL_CONSTRAINTS,
                RESOURCE_GROUPS,
                ROUTINES,
                SCHEMA_PRIVILEGES,
                SCHEMATA,
                ST_GEOMETRY_COLUMNS,
                ST_SPATIAL_REFERENCE_SYSTEMS,
                STATISTICS,
                TABLE_CONSTRAINTS,
                TABLE_PRIVILEGES,
                TABLES,
                TABLESPACES,
                TRIGGERS,
                USER_PRIVILEGES,
                VIEW_ROUTINE_USAGE,
                VIEW_TABLE_USAGE,
                VIEWS
        );
    }
}
