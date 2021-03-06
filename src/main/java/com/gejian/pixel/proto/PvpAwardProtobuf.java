// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PvpAward.proto

package com.gejian.pixel.proto;

public final class PvpAwardProtobuf {
  private PvpAwardProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface PvpAwardOrBuilder extends
      // @@protoc_insertion_point(interface_extends:PvpAward)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     **id
     * </pre>
     *
     * <code>uint32 id = 1;</code>
     */
    int getId();

    /**
     * <pre>
     **胜利个数
     * </pre>
     *
     * <code>uint32 vectory_times = 2;</code>
     */
    int getVectoryTimes();

    /**
     * <pre>
     **奖励
     * </pre>
     *
     * <code>string award = 3;</code>
     */
    java.lang.String getAward();
    /**
     * <pre>
     **奖励
     * </pre>
     *
     * <code>string award = 3;</code>
     */
    com.google.protobuf.ByteString
        getAwardBytes();
  }
  /**
   * Protobuf type {@code PvpAward}
   */
  public  static final class PvpAward extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:PvpAward)
      PvpAwardOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use PvpAward.newBuilder() to construct.
    private PvpAward(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private PvpAward() {
      id_ = 0;
      vectoryTimes_ = 0;
      award_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private PvpAward(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              id_ = input.readUInt32();
              break;
            }
            case 16: {

              vectoryTimes_ = input.readUInt32();
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              award_ = s;
              break;
            }
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.gejian.pixel.proto.PvpAwardProtobuf.internal_static_PvpAward_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.PvpAwardProtobuf.internal_static_PvpAward_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward.class, com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward.Builder.class);
    }

    public static final int ID_FIELD_NUMBER = 1;
    private int id_;
    /**
     * <pre>
     **id
     * </pre>
     *
     * <code>uint32 id = 1;</code>
     */
    public int getId() {
      return id_;
    }

    public static final int VECTORY_TIMES_FIELD_NUMBER = 2;
    private int vectoryTimes_;
    /**
     * <pre>
     **胜利个数
     * </pre>
     *
     * <code>uint32 vectory_times = 2;</code>
     */
    public int getVectoryTimes() {
      return vectoryTimes_;
    }

    public static final int AWARD_FIELD_NUMBER = 3;
    private volatile java.lang.Object award_;
    /**
     * <pre>
     **奖励
     * </pre>
     *
     * <code>string award = 3;</code>
     */
    public java.lang.String getAward() {
      java.lang.Object ref = award_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        award_ = s;
        return s;
      }
    }
    /**
     * <pre>
     **奖励
     * </pre>
     *
     * <code>string award = 3;</code>
     */
    public com.google.protobuf.ByteString
        getAwardBytes() {
      java.lang.Object ref = award_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        award_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (id_ != 0) {
        output.writeUInt32(1, id_);
      }
      if (vectoryTimes_ != 0) {
        output.writeUInt32(2, vectoryTimes_);
      }
      if (!getAwardBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, award_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (id_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(1, id_);
      }
      if (vectoryTimes_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, vectoryTimes_);
      }
      if (!getAwardBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, award_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward other = (com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward) obj;

      boolean result = true;
      result = result && (getId()
          == other.getId());
      result = result && (getVectoryTimes()
          == other.getVectoryTimes());
      result = result && getAward()
          .equals(other.getAward());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId();
      hash = (37 * hash) + VECTORY_TIMES_FIELD_NUMBER;
      hash = (53 * hash) + getVectoryTimes();
      hash = (37 * hash) + AWARD_FIELD_NUMBER;
      hash = (53 * hash) + getAward().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code PvpAward}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:PvpAward)
        com.gejian.pixel.proto.PvpAwardProtobuf.PvpAwardOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.PvpAwardProtobuf.internal_static_PvpAward_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.PvpAwardProtobuf.internal_static_PvpAward_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward.class, com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        id_ = 0;

        vectoryTimes_ = 0;

        award_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.PvpAwardProtobuf.internal_static_PvpAward_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward getDefaultInstanceForType() {
        return com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward build() {
        com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward buildPartial() {
        com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward result = new com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward(this);
        result.id_ = id_;
        result.vectoryTimes_ = vectoryTimes_;
        result.award_ = award_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return (Builder) super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward) {
          return mergeFrom((com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward other) {
        if (other == com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward.getDefaultInstance()) return this;
        if (other.getId() != 0) {
          setId(other.getId());
        }
        if (other.getVectoryTimes() != 0) {
          setVectoryTimes(other.getVectoryTimes());
        }
        if (!other.getAward().isEmpty()) {
          award_ = other.award_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int id_ ;
      /**
       * <pre>
       **id
       * </pre>
       *
       * <code>uint32 id = 1;</code>
       */
      public int getId() {
        return id_;
      }
      /**
       * <pre>
       **id
       * </pre>
       *
       * <code>uint32 id = 1;</code>
       */
      public Builder setId(int value) {
        
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       **id
       * </pre>
       *
       * <code>uint32 id = 1;</code>
       */
      public Builder clearId() {
        
        id_ = 0;
        onChanged();
        return this;
      }

      private int vectoryTimes_ ;
      /**
       * <pre>
       **胜利个数
       * </pre>
       *
       * <code>uint32 vectory_times = 2;</code>
       */
      public int getVectoryTimes() {
        return vectoryTimes_;
      }
      /**
       * <pre>
       **胜利个数
       * </pre>
       *
       * <code>uint32 vectory_times = 2;</code>
       */
      public Builder setVectoryTimes(int value) {
        
        vectoryTimes_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       **胜利个数
       * </pre>
       *
       * <code>uint32 vectory_times = 2;</code>
       */
      public Builder clearVectoryTimes() {
        
        vectoryTimes_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object award_ = "";
      /**
       * <pre>
       **奖励
       * </pre>
       *
       * <code>string award = 3;</code>
       */
      public java.lang.String getAward() {
        java.lang.Object ref = award_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          award_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       **奖励
       * </pre>
       *
       * <code>string award = 3;</code>
       */
      public com.google.protobuf.ByteString
          getAwardBytes() {
        java.lang.Object ref = award_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          award_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       **奖励
       * </pre>
       *
       * <code>string award = 3;</code>
       */
      public Builder setAward(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        award_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       **奖励
       * </pre>
       *
       * <code>string award = 3;</code>
       */
      public Builder clearAward() {
        
        award_ = getDefaultInstance().getAward();
        onChanged();
        return this;
      }
      /**
       * <pre>
       **奖励
       * </pre>
       *
       * <code>string award = 3;</code>
       */
      public Builder setAwardBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        award_ = value;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:PvpAward)
    }

    // @@protoc_insertion_point(class_scope:PvpAward)
    private static final com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward();
    }

    public static com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<PvpAward>
        PARSER = new com.google.protobuf.AbstractParser<PvpAward>() {
      @java.lang.Override
      public PvpAward parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PvpAward(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<PvpAward> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<PvpAward> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.PvpAwardProtobuf.PvpAward getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PvpAward_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_PvpAward_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016PvpAward.proto\"<\n\010PvpAward\022\n\n\002id\030\001 \001(\r" +
      "\022\025\n\rvectory_times\030\002 \001(\r\022\r\n\005award\030\003 \001(\tB*" +
      "\n\026com.gejian.pixel.protoB\020PvpAwardProtob" +
      "ufb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_PvpAward_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_PvpAward_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PvpAward_descriptor,
        new java.lang.String[] { "Id", "VectoryTimes", "Award", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
