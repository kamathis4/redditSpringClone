package com.sample.redditbackend.entities

import com.sample.redditbackend.utils.SpringRequiredUtils.ENTITY_HASHCODE
import jakarta.persistence.*
import org.hibernate.Hibernate
import org.jetbrains.annotations.NotNull

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    open lateinit var id: String

    override fun hashCode(): Int = ENTITY_HASHCODE

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        return this.id == (other as BaseEntity).id
    }
}