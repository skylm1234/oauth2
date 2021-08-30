// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ConstRanklistHonorTableItemEx.proto

package com.gejian.pixel.proto;

public final class ConstRanklistHonorTableItemExProtobuf {
  private ConstRanklistHonorTableItemExProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ConstRanklistHonorTableItemExOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ConstRanklistHonorTableItemEx)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>uint32 id = 1;</code>
     */
    int getId();

    /**
     * <code>uint32 section_fomula = 2;</code>
     */
    int getSectionFomula();

    /**
     * <code>string section = 3;</code>
     */
    java.lang.String getSection();
    /**
     * <code>string section = 3;</code>
     */
    com.google.protobuf.ByteString
        getSectionBytes();

    /**
     * <code>string award_fomula = 4;</code>
     */
    java.lang.String getAwardFomula();
    /**
     * <code>string award_fomula = 4;</code>
     */
    com.google.protobuf.ByteString
        getAwardFomulaBytes();

    /**
     * <code>string award = 5;</code>
     */
    java.lang.String getAward();
    /**
     * <code>string award = 5;</code>
     */
    com.google.protobuf.ByteString
        getAwardBytes();

    /**
     * <code>string time = 6;</code>
     */
    java.lang.String getTime();
    /**
     * <code>string time = 6;</code>
     */
    com.google.protobuf.ByteString
        getTimeBytes();
  }
  /**
   * Protobuf type {@code ConstRanklistHonorTableItemEx}
   */
  public  static final class ConstRanklistHonorTableItemEx extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ConstRanklistHonorTableItemEx)
      ConstRanklistHonorTableItemExOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ConstRanklistHonorTableItemEx.newBuilder() to construct.
    private ConstRanklistHonorTableItemEx(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ConstRanklistHonorTableItemEx() {
      id_ = 0;
      sectionFomula_ = 0;
      section_ = "";
      awardFomula_ = "";
      award_ = "";
      time_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ConstRanklistHonorTableItemEx(
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

              sectionFomula_ = input.readUInt32();
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              section_ = s;
              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              awardFomula_ = s;
              break;
            }
            case 42: {
              java.lang.String s = input.readStringRequireUtf8();

              award_ = s;
              break;
            }
            case 50: {
              java.lang.String s = input.readStringRequireUtf8();

              time_ = s;
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
      return com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.internal_static_ConstRanklistHonorTableItemEx_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.internal_static_ConstRanklistHonorTableItemEx_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx.class, com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx.Builder.class);
    }

    public static final int ID_FIELD_NUMBER = 1;
    private int id_;
    /**
     * <code>uint32 id = 1;</code>
     */
    public int getId() {
      return id_;
    }

    public static final int SECTION_FOMULA_FIELD_NUMBER = 2;
    private int sectionFomula_;
    /**
     * <code>uint32 section_fomula = 2;</code>
     */
    public int getSectionFomula() {
      return sectionFomula_;
    }

    public static final int SECTION_FIELD_NUMBER = 3;
    private volatile java.lang.Object section_;
    /**
     * <code>string section = 3;</code>
     */
    public java.lang.String getSection() {
      java.lang.Object ref = section_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        section_ = s;
        return s;
      }
    }
    /**
     * <code>string section = 3;</code>
     */
    public com.google.protobuf.ByteString
        getSectionBytes() {
      java.lang.Object ref = section_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        section_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int AWARD_FOMULA_FIELD_NUMBER = 4;
    private volatile java.lang.Object awardFomula_;
    /**
     * <code>string award_fomula = 4;</code>
     */
    public java.lang.String getAwardFomula() {
      java.lang.Object ref = awardFomula_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        awardFomula_ = s;
        return s;
      }
    }
    /**
     * <code>string award_fomula = 4;</code>
     */
    public com.google.protobuf.ByteString
        getAwardFomulaBytes() {
      java.lang.Object ref = awardFomula_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        awardFomula_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int AWARD_FIELD_NUMBER = 5;
    private volatile java.lang.Object award_;
    /**
     * <code>string award = 5;</code>
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
     * <code>string award = 5;</code>
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

    public static final int TIME_FIELD_NUMBER = 6;
    private volatile java.lang.Object time_;
    /**
     * <code>string time = 6;</code>
     */
    public java.lang.String getTime() {
      java.lang.Object ref = time_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        time_ = s;
        return s;
      }
    }
    /**
     * <code>string time = 6;</code>
     */
    public com.google.protobuf.ByteString
        getTimeBytes() {
      java.lang.Object ref = time_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        time_ = b;
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
      if (sectionFomula_ != 0) {
        output.writeUInt32(2, sectionFomula_);
      }
      if (!getSectionBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, section_);
      }
      if (!getAwardFomulaBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, awardFomula_);
      }
      if (!getAwardBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 5, award_);
      }
      if (!getTimeBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 6, time_);
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
      if (sectionFomula_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, sectionFomula_);
      }
      if (!getSectionBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, section_);
      }
      if (!getAwardFomulaBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, awardFomula_);
      }
      if (!getAwardBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, award_);
      }
      if (!getTimeBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, time_);
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
      if (!(obj instanceof com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx other = (com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx) obj;

      boolean result = true;
      result = result && (getId()
          == other.getId());
      result = result && (getSectionFomula()
          == other.getSectionFomula());
      result = result && getSection()
          .equals(other.getSection());
      result = result && getAwardFomula()
          .equals(other.getAwardFomula());
      result = result && getAward()
          .equals(other.getAward());
      result = result && getTime()
          .equals(other.getTime());
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
      hash = (37 * hash) + SECTION_FOMULA_FIELD_NUMBER;
      hash = (53 * hash) + getSectionFomula();
      hash = (37 * hash) + SECTION_FIELD_NUMBER;
      hash = (53 * hash) + getSection().hashCode();
      hash = (37 * hash) + AWARD_FOMULA_FIELD_NUMBER;
      hash = (53 * hash) + getAwardFomula().hashCode();
      hash = (37 * hash) + AWARD_FIELD_NUMBER;
      hash = (53 * hash) + getAward().hashCode();
      hash = (37 * hash) + TIME_FIELD_NUMBER;
      hash = (53 * hash) + getTime().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx parseFrom(
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
    public static Builder newBuilder(com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx prototype) {
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
     * Protobuf type {@code ConstRanklistHonorTableItemEx}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ConstRanklistHonorTableItemEx)
        com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemExOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.internal_static_ConstRanklistHonorTableItemEx_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.internal_static_ConstRanklistHonorTableItemEx_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx.class, com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx.newBuilder()
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

        sectionFomula_ = 0;

        section_ = "";

        awardFomula_ = "";

        award_ = "";

        time_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.internal_static_ConstRanklistHonorTableItemEx_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx getDefaultInstanceForType() {
        return com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx build() {
        com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx buildPartial() {
        com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx result = new com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx(this);
        result.id_ = id_;
        result.sectionFomula_ = sectionFomula_;
        result.section_ = section_;
        result.awardFomula_ = awardFomula_;
        result.award_ = award_;
        result.time_ = time_;
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
        if (other instanceof com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx) {
          return mergeFrom((com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx other) {
        if (other == com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx.getDefaultInstance()) return this;
        if (other.getId() != 0) {
          setId(other.getId());
        }
        if (other.getSectionFomula() != 0) {
          setSectionFomula(other.getSectionFomula());
        }
        if (!other.getSection().isEmpty()) {
          section_ = other.section_;
          onChanged();
        }
        if (!other.getAwardFomula().isEmpty()) {
          awardFomula_ = other.awardFomula_;
          onChanged();
        }
        if (!other.getAward().isEmpty()) {
          award_ = other.award_;
          onChanged();
        }
        if (!other.getTime().isEmpty()) {
          time_ = other.time_;
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
        com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx) e.getUnfinishedMessage();
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
       * <code>uint32 id = 1;</code>
       */
      public int getId() {
        return id_;
      }
      /**
       * <code>uint32 id = 1;</code>
       */
      public Builder setId(int value) {
        
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 id = 1;</code>
       */
      public Builder clearId() {
        
        id_ = 0;
        onChanged();
        return this;
      }

      private int sectionFomula_ ;
      /**
       * <code>uint32 section_fomula = 2;</code>
       */
      public int getSectionFomula() {
        return sectionFomula_;
      }
      /**
       * <code>uint32 section_fomula = 2;</code>
       */
      public Builder setSectionFomula(int value) {
        
        sectionFomula_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 section_fomula = 2;</code>
       */
      public Builder clearSectionFomula() {
        
        sectionFomula_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object section_ = "";
      /**
       * <code>string section = 3;</code>
       */
      public java.lang.String getSection() {
        java.lang.Object ref = section_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          section_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string section = 3;</code>
       */
      public com.google.protobuf.ByteString
          getSectionBytes() {
        java.lang.Object ref = section_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          section_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string section = 3;</code>
       */
      public Builder setSection(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        section_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string section = 3;</code>
       */
      public Builder clearSection() {
        
        section_ = getDefaultInstance().getSection();
        onChanged();
        return this;
      }
      /**
       * <code>string section = 3;</code>
       */
      public Builder setSectionBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        section_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object awardFomula_ = "";
      /**
       * <code>string award_fomula = 4;</code>
       */
      public java.lang.String getAwardFomula() {
        java.lang.Object ref = awardFomula_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          awardFomula_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string award_fomula = 4;</code>
       */
      public com.google.protobuf.ByteString
          getAwardFomulaBytes() {
        java.lang.Object ref = awardFomula_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          awardFomula_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string award_fomula = 4;</code>
       */
      public Builder setAwardFomula(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        awardFomula_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string award_fomula = 4;</code>
       */
      public Builder clearAwardFomula() {
        
        awardFomula_ = getDefaultInstance().getAwardFomula();
        onChanged();
        return this;
      }
      /**
       * <code>string award_fomula = 4;</code>
       */
      public Builder setAwardFomulaBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        awardFomula_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object award_ = "";
      /**
       * <code>string award = 5;</code>
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
       * <code>string award = 5;</code>
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
       * <code>string award = 5;</code>
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
       * <code>string award = 5;</code>
       */
      public Builder clearAward() {
        
        award_ = getDefaultInstance().getAward();
        onChanged();
        return this;
      }
      /**
       * <code>string award = 5;</code>
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

      private java.lang.Object time_ = "";
      /**
       * <code>string time = 6;</code>
       */
      public java.lang.String getTime() {
        java.lang.Object ref = time_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          time_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string time = 6;</code>
       */
      public com.google.protobuf.ByteString
          getTimeBytes() {
        java.lang.Object ref = time_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          time_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string time = 6;</code>
       */
      public Builder setTime(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        time_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string time = 6;</code>
       */
      public Builder clearTime() {
        
        time_ = getDefaultInstance().getTime();
        onChanged();
        return this;
      }
      /**
       * <code>string time = 6;</code>
       */
      public Builder setTimeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        time_ = value;
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


      // @@protoc_insertion_point(builder_scope:ConstRanklistHonorTableItemEx)
    }

    // @@protoc_insertion_point(class_scope:ConstRanklistHonorTableItemEx)
    private static final com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx();
    }

    public static com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ConstRanklistHonorTableItemEx>
        PARSER = new com.google.protobuf.AbstractParser<ConstRanklistHonorTableItemEx>() {
      @java.lang.Override
      public ConstRanklistHonorTableItemEx parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ConstRanklistHonorTableItemEx(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ConstRanklistHonorTableItemEx> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ConstRanklistHonorTableItemEx> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.ConstRanklistHonorTableItemExProtobuf.ConstRanklistHonorTableItemEx getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ConstRanklistHonorTableItemEx_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ConstRanklistHonorTableItemEx_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n#ConstRanklistHonorTableItemEx.proto\"\207\001" +
      "\n\035ConstRanklistHonorTableItemEx\022\n\n\002id\030\001 " +
      "\001(\r\022\026\n\016section_fomula\030\002 \001(\r\022\017\n\007section\030\003" +
      " \001(\t\022\024\n\014award_fomula\030\004 \001(\t\022\r\n\005award\030\005 \001(" +
      "\t\022\014\n\004time\030\006 \001(\tB?\n\026com.gejian.pixel.prot" +
      "oB%ConstRanklistHonorTableItemExProtobuf" +
      "b\006proto3"
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
    internal_static_ConstRanklistHonorTableItemEx_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ConstRanklistHonorTableItemEx_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ConstRanklistHonorTableItemEx_descriptor,
        new java.lang.String[] { "Id", "SectionFomula", "Section", "AwardFomula", "Award", "Time", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
